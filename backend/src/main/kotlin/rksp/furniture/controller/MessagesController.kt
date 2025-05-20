package rksp.furniture.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import rksp.furniture.service.MessageService

@Controller
class MessagesController(
    private val messageService: MessageService
) {

    @GetMapping("/messages")
    fun messagesPage(model: Model, request: HttpServletRequest): String {
        model.addAttribute("currentPath", request.requestURI)

        val username = SecurityContextHolder.getContext().authentication.name
        val conversations = messageService.getConversationsForUser(username)
        model.addAttribute("conversations", conversations)

        return "messages"
    }
}
