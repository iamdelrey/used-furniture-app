package rksp.furniture.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import rksp.furniture.repository.FurnitureRepository

@Controller
class CatalogController(
    private val furnitureRepository: FurnitureRepository
) {

    @GetMapping("/catalog")
    fun catalogPage(model: Model, request: HttpServletRequest): String {
        model.addAttribute("items", furnitureRepository.findAll())
        model.addAttribute("currentPath", request.requestURI)
        return "catalog"
    }
}
