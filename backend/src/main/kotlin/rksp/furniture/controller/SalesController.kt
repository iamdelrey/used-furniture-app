package rksp.furniture.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import jakarta.servlet.http.HttpServletRequest

@Controller
class SalesController {
    @GetMapping("/sales")
    fun salesPage(model: Model, request: HttpServletRequest): String {
        model.addAttribute("currentPath", request.requestURI)
        return "sales"
    }
}
