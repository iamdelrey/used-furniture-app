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
            const response = await axios.post(
                process.env.REACT_APP_API_URL
                    ? `${process.env.REACT_APP_API_URL}/api/items`
                    : '/api/items',
                {
                    ...form,
                    price: parseFloat(form.price), // ⚠️ важно: не строка
                }
            )
            onItemAdded?.(response.data)
            alert('Товар добавлен!')

            setForm({
                title: '',
                description: '',
                price: '',
                seller: '',
                condition: '',
            })
        } catch (err) {
            console.error('Ошибка при добавлении:', err)
            alert('Ошибка при добавлении товара')
        }
    }

    return (
        <form onSubmit={handleSubmit}>
            <h2>Добавить товар</h2>
            <input name="title" value={form.title} onChange={handleChange} placeholder="Название" required /><br />
            <input name="description" value={form.description} onChange={handleChange} placeholder="Описание" required /><br />
            <input name="price" value={form.price} onChange={handleChange} type="number" placeholder="Цена" required /><br />
            <input name="seller" value={form.seller} onChange={handleChange} placeholder="Продавец" required /><br />
            <input name="condition" value={form.condition} onChange={handleChange} placeholder="Состояние" required /><br />
            <button type="submit">Добавить</button>
        </form>
    )
}

export default AddItemForm
