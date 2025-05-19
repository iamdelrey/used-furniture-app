package rksp.furniture.controller

import org.springframework.web.bind.annotation.*
import rksp.furniture.model.FurnitureItem
import rksp.furniture.repository.FurnitureRepository

@RestController
@RequestMapping("/api/catalog")
class CatalogApiController(
    private val furnitureRepository: FurnitureRepository
) {
    @GetMapping
    fun getCatalog(): List<FurnitureItem> = furnitureRepository.findAll()
}
