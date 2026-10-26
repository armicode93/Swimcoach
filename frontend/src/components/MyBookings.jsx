
import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { useAuth } from '../AuthContext'
import './MyBookings.css'

function MyBookings() {
    const { token } = useAuth()

    const [bookings, setBookings] = useState([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState('')

    useEffect(() => {
        async function fetchBookings() {
            try {
                const response = await fetch('/api/bookings/my-bookings', {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                })

                if (!response.ok) {
                    throw new Error('Unable to load your bookings.')
                }

                const data = await response.json()
                setBookings(data)
            } catch (err) {
                setError(err.message || 'Something went wrong.')
            } finally {
                setLoading(false)
            }
        }

        if (token) {
            fetchBookings()
        } else {
            setError('Please log in to view your bookings.')
            setLoading(false)
        }
    }, [token])

    function formatDate(date) {
        return new Date(`${date}T00:00:00`).toLocaleDateString('en-GB', {
            day: 'numeric',
            month: 'long',
            year: 'numeric'
        })
    }

    function formatTime(time) {
        return time ? time.slice(0, 5) : ''
    }

    if (loading) {
        return (
            <main className="bookings-page">
                <p>Loading your bookings...</p>
            </main>
        )
    }

    if (error) {
        return (
            <main className="bookings-page">
                <section className="bookings-card">
                    <h1>My Bookings</h1>
                    <p className="bookings-error">{error}</p>
                    <Link to="/login" className="bookings-button">
                        Log In
                    </Link>
                </section>
            </main>
        )
    }

    return (
        <main className="bookings-page">
            <section className="bookings-card">
                <h1>My Bookings</h1>
                <p className="bookings-subtitle">
                    View and manage your swimming lessons.
                </p>

                {bookings.length === 0 ? (
                    <div className="bookings-empty">
                        <h2>No bookings yet</h2>
                        <p>
                            You haven't booked any swimming lessons yet.
                            Explore our coaches to find the right lesson for you.
                        </p>
                        <Link to="/coaches" className="bookings-button">
                            Explore Coaches
                        </Link>
                    </div>
                ) : (
                    <div className="bookings-list">
                        {bookings.map(booking => (
                            <article className="booking-item" key={booking.id}>
                                <div className="booking-header">
                                    <h2>
                                        {booking.course?.name || 'Swimming Lesson'}
                                    </h2>
                                    <span
                                        className={`booking-status status-${booking.status?.toLowerCase()}`}
                                    >
                                        {booking.status}
                                    </span>
                                </div>

                                <p>
                                    <strong>Coach:</strong>{' '}
                                    {booking.coach?.firstName || ''}{' '}
                                    {booking.coach?.lastName || booking.coach?.lastname || ''}
                                </p>

                                <p>
                                    <strong>Date:</strong>{' '}
                                    {booking.bookingDate
                                        ? formatDate(booking.bookingDate)
                                        : 'Not specified'}
                                </p>

                                <p>
                                    <strong>Time:</strong>{' '}
                                    {formatTime(booking.startTime)} – {formatTime(booking.endTime)}
                                </p>

                                {booking.comment && (
                                    <p>
                                        <strong>Comment:</strong> {booking.comment}
                                    </p>
                                )}
                            </article>
                        ))}
                    </div>
                )}
            </section>
        </main>
    )
}

export default MyBookings
