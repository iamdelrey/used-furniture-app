package rksp.furniture.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import rksp.furniture.model.User
import rksp.furniture.repository.UserRepository

@Controller
class AuthController(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager
) {

    @GetMapping("/login")
    fun loginPage(): String = "login"

    @PostMapping("/login")
    fun login(
        @RequestParam username: String,
        @RequestParam password: String,
        model: Model,
        request: HttpServletRequest
    ): String {
        return try {
            val authToken = UsernamePasswordAuthenticationToken(username, password)
            val auth = authenticationManager.authenticate(authToken)

            SecurityContextHolder.getContext().authentication = auth

            request.getSession(true)

            "redirect:/home"
        } catch (e: Exception) {
            model.addAttribute("error", "Неверное имя пользователя или пароль")
            "login"
        }
    }

    @GetMapping("/register")
    fun registerPage(): String = "register"

    @PostMapping("/register")
    fun register(
        @RequestParam username: String,
        @RequestParam password: String,
        model: Model
    ): String {
        if (userRepository.findByUsername(username).isPresent) {
            model.addAttribute("error", "Пользователь уже существует")
            return "register"
        }

        val user = User(username = username, password = passwordEncoder.encode(password))
        userRepository.save(user)

        return "redirect:/login"
    }
}