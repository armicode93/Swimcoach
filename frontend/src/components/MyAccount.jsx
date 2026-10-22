
import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { useAuth } from '../AuthContext'
import './MyAccount.css'

function MyAccount() {
    const { token } = useAuth()

    const [user, setUser] = useState(null)
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState('')
    const [editing, setEditing] = useState(false)
    const [saving, setSaving] = useState(false)
    const [message, setMessage] = useState('')

    const [form, setForm] = useState({
        firstName: '',
        lastName: '',
        phone: ''
    })

    useEffect(() => {
        async function fetchProfile() {
            try {
                const response = await fetch('/api/users/me', {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                })

                if (!response.ok) {
                    throw new Error('Unable to load your profile.')
                }

                const data = await response.json()
                setUser(data)
                setForm({
                    firstName: data.firstName || '',
                    lastName: data.lastName || '',
                    phone: data.phone || ''
                })
            } catch (err) {
                setError(err.message || 'Something went wrong.')
            } finally {
                setLoading(false)
            }
        }

        if (token) {
            fetchProfile()
        } else {
            setError('Please log in to view your account.')
            setLoading(false)
        }
    }, [token])

    function handleChange(event) {
        const { name, value } = event.target
        setForm(previous => ({
            ...previous,
            [name]: value
        }))
    }

    function handleCancel() {
        setForm({
            firstName: user.firstName || '',
            lastName: user.lastName || '',
            phone: user.phone || ''
        })
        setEditing(false)
        setError('')
        setMessage('')
    }

    async function handleSave(event) {
        event.preventDefault()
        setError('')
        setMessage('')
        setSaving(true)

        try {
            const response = await fetch('/api/users/me', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`
                },
                body: JSON.stringify(form)
            })

            const data = await response.json().catch(() => ({}))

            if (!response.ok) {
                throw new Error(data.message || 'Unable to update your profile.')
            }

            setUser(data)
            setForm({
                firstName: data.firstName || '',
                lastName: data.lastName || '',
                phone: data.phone || ''
            })
            setEditing(false)
            setMessage('Your profile has been updated successfully.')
        } catch (err) {
            setError(err.message || 'Something went wrong.')
        } finally {
            setSaving(false)
        }
    }

    if (loading) {
        return (
            <main className="account-page">
                <p>Loading your profile...</p>
            </main>
        )
    }

    if (error && !user) {
        return (
            <main className="account-page">
                <div className="account-card">
                    <p className="account-error">{error}</p>
                    <Link to="/login" className="account-button">Log In</Link>
                </div>
            </main>
        )
    }

    return (
        <main className="account-page">
            <section className="account-card">
                <div className="account-avatar">
                    {user.firstName?.charAt(0)?.toUpperCase()}
                </div>

                <h1>My Account</h1>
                <p className="account-subtitle">
                    Manage your personal information.
                </p>

                {message && (
                    <p className="account-success" role="status">
                        {message}
                    </p>
                )}

                {error && (
                    <p className="account-error" role="alert">
                        {error}
                    </p>
                )}

                {editing ? (
                    <form onSubmit={handleSave}>
                        <div className="account-form-group">
                            <label htmlFor="firstName">First Name</label>
                            <input
                                id="firstName"
                                name="firstName"
                                value={form.firstName}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="account-form-group">
                            <label htmlFor="lastName">Last Name</label>
                            <input
                                id="lastName"
                                name="lastName"
                                value={form.lastName}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="account-form-group">
                            <label htmlFor="phone">Phone Number</label>
                            <input
                                id="phone"
                                name="phone"
                                type="tel"
                                value={form.phone}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="account-actions">
                            <button
                                type="submit"
                                className="account-button"
                                disabled={saving}
                            >
                                {saving ? 'Saving...' : 'Save Changes'}
                            </button>

                            <button
                                type="button"
                                className="account-cancel-button"
                                onClick={handleCancel}
                                disabled={saving}
                            >
                                Cancel
                            </button>
                        </div>
                    </form>
                ) : (
                    <>
                        <div className="account-details">
                            <div>
                                <span>First Name</span>
                                <p>{user.firstName}</p>
                            </div>

                            <div>
                                <span>Last Name</span>
                                <p>{user.lastName}</p>
                            </div>

                            <div>
                                <span>Email Address</span>
                                <p>{user.email}</p>
                            </div>

                            <div>
                                <span>Phone Number</span>
                                <p>{user.phone || 'Not provided'}</p>
                            </div>
                        </div>

                        <button
                            type="button"
                            className="account-button"
                            onClick={() => {
                                setMessage('')
                                setError('')
                                setEditing(true)
                            }}
                        >
                            Edit Profile
                        </button>
                    </>
                )}
            </section>
        </main>
    )
}

export default MyAccount
