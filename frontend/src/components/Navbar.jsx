import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { useAuth } from '../AuthContext'
import './Navbar.css'

function Navbar() {
    const { isAuthenticated, logout } = useAuth()
    const [message, setMessage] = useState('')

    useEffect(() => {
        if (isAuthenticated) {
            setMessage('')
        }
    }, [isAuthenticated])

    function handleLogout() {
        logout()
        setMessage('You have logged out successfully.')
    }

    return (
        <>
            <nav className="navbar">

                <div className="logo">
                    <Link to="/">SwimCoach</Link>
                </div>

                <div className="nav-links">
                    <Link to="/">Home</Link>
                    <Link to="/coaches">Coaches</Link>
                    <Link to="/courses">Courses</Link>

                    {isAuthenticated && (
                        <Link to="/account">My Account</Link>
                    )}

                </div>

                <div className="nav-auth">
                    {isAuthenticated ? (
                        <button
                            type="button"
                            className="login-link"
                            onClick={handleLogout}
                        >
                            Log Out
                        </button>
                    ) : (
                        <>
                            <Link to="/login" className="login-link">
                                Log In
                            </Link>

                            <Link to="/register" className="register-button">
                                Sign Up
                            </Link>
                        </>
                    )}
                </div>

            </nav>

            {message && (
                <p className="auth-success">{message}</p>
            )}
        </>
    )
}

export default Navbar
