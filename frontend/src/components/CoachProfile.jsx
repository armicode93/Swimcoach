
import { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
import './CoachProfile.css'

function CoachProfile() {
    const { id } = useParams()
    const [coach, setCoach] = useState(null)
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState(false)

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

    if (loading) {
        return <p className="profile-message">Loading coach profile...</p>
    }

    if (error || !coach) {
        return <p className="profile-message">Coach profile not found.</p>
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
                            <a href={`tel:${coach.phone}`}>{coach.phone}</a>
                        </p>
                    )}


                    {(coach.instagram || coach.facebook) && (
                        <div className="profile-socials">
                            <strong>Social media:</strong>

                            <div className="social-links">
                                {coach.instagram && (
                                    <a
                                        href={coach.instagram.startsWith('http')
                                            ? coach.instagram
                                            : `https://instagram.com/${coach.instagram.replace(/^@/, '')}`}
                                        target="_blank"
                                        rel="noopener noreferrer"
                                    >
                                        Instagram <div></div>
                                    </a>
                                )}

                                {coach.facebook && (
                                    <a
                                        href={coach.facebook.startsWith('http')
                                            ? coach.facebook
                                            : `https://facebook.com/${coach.facebook}`}
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
        </section>
    )
}

export default CoachProfile
