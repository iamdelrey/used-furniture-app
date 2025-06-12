package rksp.furniture.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import rksp.furniture.model.FurnitureItem
import rksp.furniture.repository.FurnitureRepository
import rksp.furniture.service.FileStorageService

@Controller
class SalesController(
    private val furnitureRepository: FurnitureRepository,
    private val fileStorageService: FileStorageService
) {

    @GetMapping("/sales")
    fun salesPage(model: Model, request: HttpServletRequest): String {
        val username = SecurityContextHolder.getContext().authentication.name
        model.addAttribute("currentPath", request.requestURI)
        model.addAttribute("items", furnitureRepository.findBySeller(username))
        return "sales"
    }

    @GetMapping("/sales/new")
    fun newSalePage(model: Model, request: HttpServletRequest): String {
        model.addAttribute("currentPath", request.requestURI)
        model.addAttribute("furnitureItem", FurnitureItem())
        model.addAttribute(
            "conditions", listOf("Новый", "Очень хорошее", "Хорошее", "Удовлетворительное", "Б/У")
        )
        return "sale_form"
    }

    @PostMapping("/sales")
    fun createSale(
        @ModelAttribute furnitureItem: FurnitureItem,
        @RequestParam("image", required = false) file: MultipartFile?
    ): String {
        val username = SecurityContextHolder.getContext().authentication.name
        furnitureItem.seller = username
        val saved = furnitureRepository.save(furnitureItem)
        if (file != null && !file.isEmpty) {
            val fname = fileStorageService.store(file)
            saved.photoFilename = fname
            furnitureRepository.save(saved)
        }
        return "redirect:/sales"
    }

    @GetMapping("/sales/edit/{id}")
    fun editSalePage(
        @PathVariable id: Long,
        model: Model,
        request: HttpServletRequest
    ): String {
        val item = furnitureRepository.findById(id).orElseThrow()
        model.addAttribute("currentPath", request.requestURI)
        model.addAttribute("furnitureItem", item)
        model.addAttribute(
            "conditions", listOf("Новый", "Очень хорошее", "Хорошее", "Удовлетворительное", "Б/У")
        )
        return "sale_form"
    }

    @PostMapping("/sales/update/{id}")
    fun updateSale(
        @PathVariable id: Long,
        @ModelAttribute furnitureItem: FurnitureItem,
        @RequestParam("image", required = false) file: MultipartFile?
    ): String {
        val existing = furnitureRepository.findById(id).orElseThrow()
        existing.title = furnitureItem.title
        existing.description = furnitureItem.description
        existing.price = furnitureItem.price
        existing.condition = furnitureItem.condition
        if (file != null && !file.isEmpty) {
            existing.photoFilename = fileStorageService.store(file)
        }
        furnitureRepository.save(existing)
        return "redirect:/sales"
    }

    @PostMapping("/sales/delete/{id}")
    fun deleteSale(@PathVariable id: Long): String {
        furnitureRepository.deleteById(id)
        return "redirect:/sales"
    }
}
