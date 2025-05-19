package rksp.furniture.model

import jakarta.persistence.*

@Entity
data class FurnitureItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val title: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val seller: String = "",
    val condition: String = ""
)
