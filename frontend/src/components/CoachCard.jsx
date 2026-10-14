
import { Link } from 'react-router-dom'

function CoachCard({
                       id,
                       name,
                       location,
                       description,
                       profileImage,
                       yearsOfExperience,
                       specializations
                   }) {
    return (
        <div className="coach-card">

            {profileImage ? (
                <img
                    src={profileImage}
                    alt={name}
                    className="coach-image"
                    onError={(event) => {
                        event.currentTarget.style.display = 'none'
                    }}
                />
            ) : (
                <div className="coach-image-placeholder">
                    No image available
                </div>
            )}

            <div className="coach-content">
                <h3>{name}</h3>

                <p className="coach-location">
                    📍 {location}
                </p>

                <p className="coach-description">
                    {description}
                </p>

                <p>
                    <strong>{yearsOfExperience}</strong> years of experience
                </p>

                <p>{specializations}</p>

                <Link to={`/coaches/${id}`} className="view-profile-button">
                    View Profile
                </Link>
            </div>

        </div>
    )
}

export default CoachCard
