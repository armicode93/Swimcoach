import './App.css'

function App() {
  return (
      <>
        <nav className="navbar">
          <div className="logo">
            SwimCoach
          </div>

          <div className="nav-links">
            <a href="#">Coaches</a><br/>
            <a href="#">Courses</a><br/>
            <a href="#">Login</a><br/>
          </div>
        </nav>

        <main>
          <section className="hero">
            <h1>
              Trova il tuo coach di nuoto
            </h1>

            <p>
              Lezioni private, corsi e allenamenti
              con coach qualificati.
            </p>

            <button>
              Trova un coach
            </button>
          </section>
        </main>
      </>
  )
}

export default App