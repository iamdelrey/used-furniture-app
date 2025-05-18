import React, { useState } from 'react'
import axios from 'axios'

function AddItemForm({ onItemAdded }) {
    const [form, setForm] = useState({
        title: '',
        description: '',
        price: '',
        seller: '',
        condition: '',
    })

    const handleChange = e => {
        setForm({ ...form, [e.target.name]: e.target.value })
    }

    const handleSubmit = async e => {
        e.preventDefault()
        try {
            const res = await axios.post(
                process.env.REACT_APP_API_URL
                    ? `${process.env.REACT_APP_API_URL}/api/items`
                    : '/api/items',
                {
                    ...form,
                    price: parseFloat(form.price),
                }
            )
            onItemAdded(res.data)
            setForm({
                title: '',
                description: '',
                price: '',
                seller: '',
                condition: '',
            })
        } catch (err) {
            console.error('Ошибка при добавлении:', err)
        }
    }

    return (
        <form onSubmit={handleSubmit} style={{ marginBottom: '2rem' }}>
            <h2>Добавить товар</h2>
            <input
                type="text"
                name="title"
                placeholder="Название"
                value={form.title}
                onChange={handleChange}
                required
            /><br />
            <input
                type="text"
                name="description"
                placeholder="Описание"
                value={form.description}
                onChange={handleChange}
                required
            /><br />
            <input
                type="number"
                name="price"
                placeholder="Цена"
                value={form.price}
                onChange={handleChange}
                required
            /><br />
            <input
                type="text"
                name="seller"
                placeholder="Продавец"
                value={form.seller}
                onChange={handleChange}
                required
            /><br />
            <input
                type="text"
                name="condition"
                placeholder="Состояние"
                value={form.condition}
                onChange={handleChange}
                required
            /><br />
            <button type="submit">Добавить</button>
        </form>
    )
}

export default AddItemForm
