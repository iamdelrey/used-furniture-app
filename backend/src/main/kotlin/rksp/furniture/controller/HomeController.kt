﻿package rksp.furniture.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.GetMapping
import jakarta.servlet.http.HttpServletRequest

@Controller
class HomeController {

    @GetMapping("/home")
    fun home(
        @AuthenticationPrincipal user: User?,
        model: Model,
        request: HttpServletRequest
    ): String {
        model.addAttribute("username", user?.username ?: "Гость")
        model.addAttribute("currentPath", request.requestURI)
        return "home"
    }
}
