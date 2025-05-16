package rksp.furniture.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rksp.furniture.model.FurnitureItem
import rksp.furniture.repository.FurnitureRepository

@RestController
@RequestMapping("/api/items")
class FurnitureController(
    private val repository: FurnitureRepository
) {
    @GetMapping
    fun getAll(): List<FurnitureItem> = repository.findAll()

    @PostMapping
    fun create(@RequestBody item: FurnitureItem): FurnitureItem = repository.save(item)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<FurnitureItem> =
        repository.findById(id).map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        if (repository.existsById(id)) {
            repository.deleteById(id)
            ResponseEntity.ok().build()
        } else ResponseEntity.notFound().build()
}
