
import { useEffect, useState } from 'react'
import './Courses.css'
function Courses() {

    const [courses, setCourses] = useState([])
    const [error, setError] = useState(false)

    useEffect(() => {
        fetch('/api/courses')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to load courses')
                }
                return response.json()
            })
            .then(data => {
                console.log('Courses received:', data)
                setCourses(data)
            })
            .catch(error => {
                console.error('Error loading courses:', error)
                setError(true)
            })
    }, [])

    return (
        <section className="courses">
            <h2>Our Courses</h2>

            <p>
                Discover our activities and find the one
                that best suits your needs.
            </p>

            {error ? (
                <p>Unable to load courses. Please try again later.</p>
            ) : courses.length === 0 ? (
                <p>No courses available yet.</p>
            ) : (
                <div className="course-list">
                    {courses.map(course => (
                        <div className="course-card" key={course.id}>
                            <h3>{course.name}</h3>
                            <p>{course.description}</p>
                        </div>
                    ))}
                </div>
            )}
        </section>
    )
}

export default Courses
