package rksp.furniture.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import rksp.furniture.model.FurnitureItem
import rksp.furniture.repository.FurnitureRepository

@Controller
class SalesController(
    private val furnitureRepository: FurnitureRepository
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
            "conditions",
            listOf("Новый", "Очень хорошее", "Хорошее", "Удовлетворительное", "Б/У")
        )
        return "sale_form"
    }


    @PostMapping("/sales")
    fun createSale(@ModelAttribute furnitureItem: FurnitureItem): String {
        val username = SecurityContextHolder.getContext().authentication.name
        furnitureItem.seller = username
        furnitureRepository.save(furnitureItem)
        return "redirect:/sales"
    }

    @GetMapping("/sales/edit/{id}")
    fun editSalePage(@PathVariable id: Long, model: Model, request: HttpServletRequest): String {
        val item = furnitureRepository.findById(id).orElseThrow()
        model.addAttribute("currentPath", request.requestURI)
        model.addAttribute("furnitureItem", item)
        model.addAttribute("conditions", listOf("Новый", "Очень хорошее", "Хорошее", "Удовлетворительное", "Б/У"))
        return "sale_form"
    }

    @PostMapping("/sales/update/{id}")
    fun updateSale(@PathVariable id: Long, @ModelAttribute furnitureItem: FurnitureItem): String {
        val existing = furnitureRepository.findById(id).orElseThrow()
        existing.title = furnitureItem.title
        existing.description = furnitureItem.description
        existing.price = furnitureItem.price
        existing.condition = furnitureItem.condition
        furnitureRepository.save(existing)
        return "redirect:/sales"
    }

    @PostMapping("/sales/delete/{id}")
    fun deleteSale(@PathVariable id: Long): String {
        furnitureRepository.deleteById(id)
        return "redirect:/sales"
    }
}
