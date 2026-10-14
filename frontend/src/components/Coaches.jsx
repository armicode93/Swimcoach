import { useEffect, useState } from 'react'
import CoachCard from './CoachCard'
import './Coaches.css'

function Coaches() {

    const [coaches, setCoaches] = useState([])

    useEffect(() => {
        fetch('/api/coaches')
            .then(response => response.json())
            .then(data => setCoaches(data))
    }, [])

    return (
        <section className="coaches">

            <h2>Nos coachs</h2>

            <div className="coach-list">

                {coaches.map((coach) => (
                    <CoachCard
                        key={coach.id}
                        id={coach.id}
                        name={`${coach.firstName} ${coach.lastname}`}
                        location={coach.location}
                        description={coach.description}
                        profileImage={coach.profileImage}
                        yearsOfExperience={coach.yearsOfExperience}
                        specializations={coach.specializations}
                    />
                ))}

            </div>

        </section>
    )
}

export default Coaches