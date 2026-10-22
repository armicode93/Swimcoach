
import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import './Auth.css'

function Register() {
    const [form, setForm] = useState({
        firstName: '',
        lastName: '',
        email: '',
        phone: '',
        password: ''
    })

    const [error, setError] = useState('')
    const [success, setSuccess] = useState('')
    const [loading, setLoading] = useState(false)

    const navigate = useNavigate()

    function handleChange(event) {
        const { name, value } = event.target

        setForm(previous => ({
            ...previous,
            [name]: value
        }))
    }

    async function handleSubmit(event) {
        event.preventDefault()
        setError('')
        setSuccess('')
        setLoading(true)

        try {
            const response = await fetch('/api/auth/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(form)
            })


if (!response.ok) {
    const data = await response.json()

    throw new Error(
        data.message || 'Registration failed.'
    )
}


            setSuccess('Account created successfully! Redirecting to login...')

            setTimeout(() => {
                navigate('/login')
            }, 1500)
        } catch (err) {
            setError(err.message || 'Unable to connect to the server.')
        } finally {
            setLoading(false)
        }
    }

    return (
        <section className="auth-page">
            <div className="auth-card">
                <h1>Create Your Account</h1>
                <p className="auth-subtitle">
                    Join SwimCoach and get started.
                </p>

                <form onSubmit={handleSubmit}>
                    <label htmlFor="firstName">First Name</label>
                    <input
                        id="firstName"
                        name="firstName"
                        value={form.firstName}
                        onChange={handleChange}
                        placeholder="Enter your first name"
                        autoComplete="given-name"
                        required
                    />

                    <label htmlFor="lastName">Last Name</label>
                    <input
                        id="lastName"
                        name="lastName"
                        value={form.lastName}
                        onChange={handleChange}
                        placeholder="Enter your last name"
                        autoComplete="family-name"
                        required
                    />

                    <label htmlFor="registerEmail">Email Address</label>
                    <input
                        id="registerEmail"
                        name="email"
                        type="email"
                        value={form.email}
                        onChange={handleChange}
                        placeholder="Enter your email"
                        autoComplete="email"
                        required
                    />

                    <label htmlFor="phone">Phone Number</label>
                    <input
                        id="phone"
                        name="phone"
                        type="tel"
                        value={form.phone}
                        onChange={handleChange}
                        placeholder="Enter your phone number"
                        autoComplete="tel"
                        required
                    />

                    <label htmlFor="registerPassword">Password</label>
                    <input
                        id="registerPassword"
                        name="password"
                        type="password"
                        value={form.password}
                        onChange={handleChange}
                        placeholder="Create a password"
                        autoComplete="new-password"
                        required
                    />

                    {error && <p className="auth-error">{error}</p>}

                    {success && (
                        <p className="auth-success">{success}</p>
                    )}

                    <button type="submit" disabled={loading}>
                        {loading ? 'Creating account...' : 'Create Account'}
                    </button>
                </form>

                <p className="auth-footer">
                    Already have an account? <Link to="/login">Log In</Link>
                </p>
            </div>
        </section>
    )
}

export default Register
