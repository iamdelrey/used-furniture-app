package rksp.furniture.model

import jakarta.persistence.*

@Entity
@Table(name = "furniture_item")
data class FurnitureItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var title: String = "",
    var description: String = "",
    var price: Double = 0.0,
    var seller: String = "",
    var condition: String = ""
)