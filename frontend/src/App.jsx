import './App.css'
import Navbar from './components/Navbar'
import Hero from './components/Hero'
import Coaches from './components/Coaches'
import Courses from './components/Courses'
import CoachProfile from './components/CoachProfile'
import Login from './components/Login'
import Register from "./components/Register.jsx";


import { Routes, Route } from 'react-router-dom'

function App() {
    return (
        <>
            <Navbar />

            <Routes>
                <Route path="/" element={<Hero />} />
                <Route path="/coaches" element={<Coaches />} />
                <Route path="/courses" element={<Courses />} />
                <Route path="/coaches/:id" element={<CoachProfile />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
            </Routes>
        </>
    )
}

export default App