
import { useEffect, useState } from 'react'
import { Link, useParams, useNavigate } from 'react-router-dom'
import { useAuth } from '../AuthContext'
import './CoachProfile.css'

function CoachProfile() {
    const { id } = useParams()
    const { token, isAuthenticated } = useAuth()
    const navigate = useNavigate()

    const [coach, setCoach] = useState(null)
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState(false)

    // Booking states
    const [coachCourses, setCoachCourses] = useState([])
    const [selectedCoachCourse, setSelectedCoachCourse] = useState('')
    const [availabilities, setAvailabilities] = useState([])
    const [selectedDate, setSelectedDate] = useState('')
    const [selectedAvailability, setSelectedAvailability] = useState('')
    const [comment, setComment] = useState('')
    const [bookingLoading, setBookingLoading] = useState(false)
    const [bookingMessage, setBookingMessage] = useState('')
    const [bookingError, setBookingError] = useState('')

    // Load coach profile
    useEffect(() => {
        fetch(`/api/coaches/${id}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Coach not found')
                }
                return response.json()
            })
            .then(data => setCoach(data))
            .catch(error => {
                console.error('Error loading coach:', error)
                setError(true)
            })
            .finally(() => setLoading(false))
    }, [id])

    // Load courses offered by this coach
    useEffect(() => {
        async function fetchCoachCourses() {
            try {
                const response = await fetch('/api/coach-courses')

                if (!response.ok) {
                    throw new Error('Unable to load coach courses.')
                }

                const data = await response.json()

                const filteredCourses = data.filter(
                    item => String(item.coach?.id) === String(id)
                )

                setCoachCourses(filteredCourses)
            } catch (error) {
                console.error('Error loading coach courses:', error)
                setBookingError('Unable to load this coach’s courses.')
            }
        }

        fetchCoachCourses()
    }, [id])

    // Load availabilities when a course is selected
    useEffect(() => {
        async function fetchAvailabilities() {
            if (!selectedCoachCourse) {
                setAvailabilities([])
                return
            }

            const selected = coachCourses.find(
                item => String(item.id) === String(selectedCoachCourse)
            )

            if (!selected) return

            try {
                setAvailabilities([])
                setSelectedAvailability('')

                const response = await fetch(
                    `/api/availabilities/coach/${id}/course/${selected.course.id}`
                )

                if (!response.ok) {
                    throw new Error('Unable to load availabilities.')
                }

                const data = await response.json()

                setAvailabilities(data.filter(item => item.active))
            } catch (error) {
                console.error('Error loading availabilities:', error)
                setBookingError('Unable to load available time slots.')
            }
        }

        fetchAvailabilities()
    }, [selectedCoachCourse, coachCourses, id])

    const selectedCourse = coachCourses.find(
        item => String(item.id) === String(selectedCoachCourse)
    )

    // Return dates that match the coach's weekly availability
    function getAvailableDates() {
        if (!selectedCourse || availabilities.length === 0) return []

        const dates = []
        const today = new Date()

        for (let i = 0; i < 90; i++) {
            const date = new Date(
                today.getFullYear(),
                today.getMonth(),
                today.getDate() + i
            )

            const dayNames = [
                'SUNDAY',
                'MONDAY',
                'TUESDAY',
                'WEDNESDAY',
                'THURSDAY',
                'FRIDAY',
                'SATURDAY'
            ]

            const dayName = dayNames[date.getDay()]

            if (availabilities.some(a => a.dayOfWeek === dayName)) {
                const year = date.getFullYear()
                const month = String(date.getMonth() + 1).padStart(2, '0')
                const day = String(date.getDate()).padStart(2, '0')

                dates.push({
                    value: `${year}-${month}-${day}`,
                    label: date.toLocaleDateString('en-GB', {
                        weekday: 'long',
                        day: 'numeric',
                        month: 'long',
                        year: 'numeric'
                    })
                })
            }
        }

        return dates
    }

    const availableDates = getAvailableDates()

    const dateAvailabilities = availabilities.filter(
        availability => {
            if (!selectedDate) return false

            const [year, month, day] = selectedDate.split('-').map(Number)
            const date = new Date(year, month - 1, day)

            const dayNames = [
                'SUNDAY',
                'MONDAY',
                'TUESDAY',
                'WEDNESDAY',
                'THURSDAY',
                'FRIDAY',
                'SATURDAY'
            ]

            return availability.dayOfWeek === dayNames[date.getDay()]
        }
    )

    function calculateEndTime(startTime, duration) {
        const [hours, minutes] = startTime.split(':').map(Number)
        const totalMinutes = hours * 60 + minutes + duration

        const endHours = Math.floor(totalMinutes / 60) % 24
        const endMinutes = totalMinutes % 60

        return `${String(endHours).padStart(2, '0')}:${String(endMinutes).padStart(2, '0')}:00`
    }

    async function handleBooking(event) {
        event.preventDefault()

        setBookingMessage('')
        setBookingError('')

        if (!isAuthenticated || !token) {
            navigate('/login')
            return
        }

        if (!selectedCourse || !selectedDate || !selectedAvailability) {
            setBookingError('Please select a course, date and time.')
            return
        }

        const availability = dateAvailabilities.find(
            item => String(item.id) === String(selectedAvailability)
        )

        if (!availability) {
            setBookingError('Please select a valid availability.')
            return
        }

        const bookingData = {
            coachId: Number(id),
            courseId: selectedCourse.course.id,
            bookingDate: selectedDate,
            startTime: availability.startTime,
            endTime: calculateEndTime(
                availability.startTime,
                selectedCourse.duration
            ),
            comment: comment
        }

        try {
            setBookingLoading(true)

            const response = await fetch('/api/bookings', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`
                },
                body: JSON.stringify(bookingData)
            })

            if (!response.ok) {
                const errorText = await response.text()
                throw new Error(
                    errorText || 'Unable to create your booking.'
                )
            }

            setBookingMessage(
                'Your booking has been successfully submitted!'
            )

            setSelectedDate('')
            setSelectedAvailability('')
            setComment('')
        } catch (error) {
            setBookingError(error.message || 'Something went wrong.')
        } finally {
            setBookingLoading(false)
        }
    }

    if (loading) {
        return (
            <p className="profile-message">Loading coach profile...</p>
        )
    }

    if (error || !coach) {
        return (
            <p className="profile-message">Coach profile not found.</p>
        )
    }

    const fullName = `${coach.firstName ?? ''} ${coach.lastname ?? ''}`.trim()

    return (
        <section className="coach-profile">
            <Link to="/coaches" className="back-link">
                &larr; Back to coaches
            </Link>

            <div className="profile-card">
                {coach.profileImage ? (
                    <img
                        src={coach.profileImage}
                        alt={fullName}
                        className="profile-image"
                        onError={event => {
                            event.currentTarget.style.display = 'none'
                        }}
                    />
                ) : (
                    <div className="profile-image-placeholder">
                        No image available
                    </div>
                )}

                <div className="profile-details">
                    <h1>{fullName || 'Coach'}</h1>

                    {coach.location && (
                        <p className="profile-location">
                            📍 {coach.location}
                        </p>
                    )}

                    {coach.description && (
                        <p className="profile-description">
                            {coach.description}
                        </p>
                    )}

                    {coach.yearsOfExperience != null && (
                        <p>
                            <strong>Experience:</strong>{' '}
                            {coach.yearsOfExperience} years
                        </p>
                    )}

                    {coach.certifications && (
                        <p>
                            <strong>Certifications:</strong>{' '}
                            {coach.certifications}
                        </p>
                    )}

                    {coach.languages && (
                        <p>
                            <strong>Languages:</strong>{' '}
                            {coach.languages}
                        </p>
                    )}

                    {coach.specializations && (
                        <p>
                            <strong>Specializations:</strong>{' '}
                            {coach.specializations}
                        </p>
                    )}

                    {coach.phone && (
                        <p className="profile-phone">
                            <strong>Phone:</strong>{' '}
                            <a href={`tel:${coach.phone}`}>
                                {coach.phone}
                            </a>
                        </p>
                    )}

                    {(coach.instagram || coach.facebook) && (
                        <div className="profile-socials">
                            <strong>Social media:</strong>

                            <div className="social-links">
                                {coach.instagram && (
                                    <a
                                        href={
                                            coach.instagram.startsWith('http')
                                                ? coach.instagram
                                                : `https://instagram.com/${coach.instagram.replace(/^@/, '')}`
                                        }
                                        target="_blank"
                                        rel="noopener noreferrer"
                                    >
                                        Instagram
                                    </a>
                                )}

                                {coach.facebook && (
                                    <a
                                        href={
                                            coach.facebook.startsWith('http')
                                                ? coach.facebook
                                                : `https://facebook.com/${coach.facebook}`
                                        }
                                        target="_blank"
                                        rel="noopener noreferrer"
                                    >
                                        Facebook
                                    </a>
                                )}
                            </div>
                        </div>
                    )}
                </div>
            </div>

            {/* Booking section */}
            <section className="booking-section">
                <h2>Book a lesson</h2>
                <p className="booking-intro">
                    Choose a course and an available time slot.
                </p>

                {bookingError && (
                    <p className="booking-error">{bookingError}</p>
                )}

                {bookingMessage && (
                    <p className="booking-success">{bookingMessage}</p>
                )}

                {coachCourses.length === 0 ? (
                    <p>No courses are currently offered by this coach.</p>
                ) : (
                    <form onSubmit={handleBooking}>
                        <label htmlFor="course">Select a course</label>

                        <select
                            id="course"
                            value={selectedCoachCourse}
                            onChange={event => {
                                setSelectedCoachCourse(event.target.value)
                                setSelectedDate('')
                                setSelectedAvailability('')
                                setBookingError('')
                                setBookingMessage('')
                            }}
                            required
                        >
                            <option value="">Choose a course</option>

                            {coachCourses.map(item => (
                                <option key={item.id} value={item.id}>
                                    {item.course?.name || 'Course'} — €{item.price}
                                </option>
                            ))}
                        </select>

                        {selectedCourse && (
                            <p className="course-info">
                                Duration: {selectedCourse.duration} minutes
                                {' · '}
                                Price: €{selectedCourse.price}
                            </p>
                        )}

                        {selectedCourse && (
                            <>
                                <label htmlFor="booking-date">
                                    Select a date
                                </label>

                                <select
                                    id="booking-date"
                                    value={selectedDate}
                                    onChange={event => {
                                        setSelectedDate(event.target.value)
                                        setSelectedAvailability('')
                                        setBookingError('')
                                    }}
                                    required
                                >
                                    <option value="">Choose a date</option>

                                    {availableDates.map(date => (
                                        <option
                                            key={date.value}
                                            value={date.value}
                                        >
                                            {date.label}
                                        </option>
                                    ))}
                                </select>
                            </>
                        )}

                        {selectedDate && (
                            <>
                                <label htmlFor="booking-time">
                                    Select an available time
                                </label>

                                <select
                                    id="booking-time"
                                    value={selectedAvailability}
                                    onChange={event => {
                                        setSelectedAvailability(event.target.value)
                                        setBookingError('')
                                    }}
                                    required
                                >
                                    <option value="">Choose a time</option>

                                    {dateAvailabilities.map(item => (
                                        <option key={item.id} value={item.id}>
                                            {item.startTime.slice(0, 5)} – {calculateEndTime(
                                            item.startTime,
                                            selectedCourse.duration
                                        ).slice(0, 5)}
                                        </option>
                                    ))}
                                </select>
                            </>
                        )}

                        <label htmlFor="booking-comment">
                            Comment (optional)
                        </label>

                        <textarea
                            id="booking-comment"
                            value={comment}
                            onChange={event => setComment(event.target.value)}
                            placeholder="Add a message for the coach..."
                            rows="3"
                        />

                        <button
                            type="submit"
                            className="booking-submit-button"
                            disabled={bookingLoading}
                        >
                            {bookingLoading
                                ? 'Sending...'
                                : 'Confirm booking'}
                        </button>

                        {!isAuthenticated && (
                            <p className="booking-login-hint">
                                You need to log in before booking a lesson.
                                <Link to="/login"> Log in</Link>
                            </p>
                        )}
                    </form>
                )}
            </section>
        </section>
    )
}

export default CoachProfile
