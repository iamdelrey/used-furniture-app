import React from 'react'
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import AddItemForm from './components/AddItemForm'
import HomePage from './components/HomePage'

function App() {
    return (
        <Router>
            <nav style={{ padding: '1rem' }}>
                <Link to="/">Каталог</Link> | <Link to="/add">Добавить</Link>
            </nav>
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/add" element={<AddItemForm onItemAdded={() => {}} />} />
            </Routes>
        </Router>
    )
}

export default App
