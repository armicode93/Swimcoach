
import { Link } from 'react-router-dom'
import './Navbar.css'

function Navbar() {
    return (
        <nav className="navbar">

            <div className="logo">
                <Link to="/">SwimCoach</Link>
            </div>

            <div className="nav-links">
                <Link to="/">Home</Link>
                <Link to="/coaches">Coaches</Link>
                <Link to="/courses">Courses</Link>
            </div>

            <div className="nav-auth">
                <Link to="/login" className="login-link">
                    Log In
                </Link>

                <Link to="/register" className="register-button">
                    Sign Up
                </Link>
            </div>

        </nav>
    )
}

export default Navbar
// componente disponibile agli altri file