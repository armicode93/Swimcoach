
import { Link } from 'react-router-dom'

function Hero() {
    return (
        <main>
            <section className="hero">
                <h1>
                    Find Your Swimming Coach
                </h1>

                <p>
                    Private lessons, courses, and training
                    with qualified coaches.
                </p>

                <Link to="/coaches" className="hero-button">
                    Find a Coach
                </Link>
            </section>
        </main>
    )
}

export default Hero
