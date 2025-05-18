import React, {useEffect, useState} from 'react'
import axios from 'axios'

function HomePage() {
    const [items, setItems] = useState([])

    useEffect(() => {
        const fetch = async () => {
            const res = await axios.get(
                process.env.REACT_APP_API_URL
                    ? `${process.env.REACT_APP_API_URL}/api/items`
                    : '/api/items'
            )
            setItems(res.data)
        }
        fetch()
    }, [])

    return (
        <div>
            <h1>Каталог</h1>
            <ul>
                {Array.isArray(items) ? items.map(item => (
                    <li key={item.id}>
                        <strong>{item.title}</strong> — {item.condition} — {item.price} ₽
                    </li>
                )) : null}
            </ul>
        </div>
    )
}

export default HomePage
