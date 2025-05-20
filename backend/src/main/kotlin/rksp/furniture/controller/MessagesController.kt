package rksp.furniture.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import jakarta.servlet.http.HttpServletRequest

@Controller
class MessagesController {
    @GetMapping("/messages")
    fun messagesPage(model: Model, request: HttpServletRequest): String {
        model.addAttribute("currentPath", request.requestURI)
        return "messages"
    }
}
