import React, { useEffect, useState } from 'react'
import axios from 'axios'
import AddItemForm from './components/AddItemForm'

function App() {
  const [items, setItems] = useState([])

  useEffect(() => {
    fetchItems()
  }, [])

  const fetchItems = async () => {
    try {
      const res = await axios.get(
          process.env.REACT_APP_API_URL
              ? `${process.env.REACT_APP_API_URL}/api/items`
              : '/api/items'
      )
      setItems(res.data)
    } catch (err) {
      console.error('Ошибка при загрузке:', err)
    }
  }

  const handleItemAdded = newItem => {
    setItems([...items, newItem])
  }

  return (
      <div style={{ padding: '2rem' }}>
        <h1>Каталог подержанной мебели</h1>
        <AddItemForm onItemAdded={handleItemAdded} />
        <ul>
          {items.map(item => (
              <li key={item.id}>
                <strong>{item.title}</strong> — {item.condition} — {item.price} ₽
              </li>
          ))}
        </ul>
      </div>
  )
}

export default App
