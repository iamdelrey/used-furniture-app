package rksp.furniture.model

import jakarta.persistence.*

@Entity
@Table(name = "furniture_item")
data class FurnitureItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var title: String = "",
    var description: String = "",
    var price: Double = 0.0,
    var seller: String = "",
    var condition: String = "",

    @Column(name = "photo_filename")
    var photoFilename: String? = null
)