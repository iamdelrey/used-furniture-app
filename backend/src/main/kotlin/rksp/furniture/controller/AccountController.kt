package rksp.furniture.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import rksp.furniture.repository.UserRepository
import rksp.furniture.model.User

@Controller
@RequestMapping("/account")
class AccountController(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @GetMapping
    fun viewAccount(model: Model, request: HttpServletRequest): String {
        model.addAttribute("currentPath", request.requestURI)
        val username = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.findByUsername(username).orElseThrow()
        model.addAttribute("user", user)
        return "account"
    }

    @PostMapping("/update")
    fun updateAccount(
        @ModelAttribute("user") form: User,
        model: Model
    ): String {
        val username = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.findByUsername(username).orElseThrow()
        user.email = form.email
        if (form.password.isNotBlank()) {
            user.password = passwordEncoder.encode(form.password)
        }
        userRepository.save(user)
        return "redirect:/account?success"
    }
}

