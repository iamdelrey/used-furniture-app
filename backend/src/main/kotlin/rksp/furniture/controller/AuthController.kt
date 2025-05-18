package rksp.furniture.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import rksp.furniture.auth.AuthRequest
import rksp.furniture.auth.AuthResponse
import rksp.furniture.model.User
import rksp.furniture.repository.UserRepository
import rksp.furniture.security.JwtService

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager
) {

    @PostMapping("/register")
    fun register(@RequestBody request: AuthRequest): ResponseEntity<AuthResponse> {
        if (userRepository.findByUsername(request.username).isPresent) {
            return ResponseEntity.badRequest().build()
        }

        val user = User(
            username = request.username,
            password = passwordEncoder.encode(request.password)
        )
        userRepository.save(user)

        val token = jwtService.generateToken(user.username)
        return ResponseEntity.ok(AuthResponse(token))
    }

    @PostMapping("/login")
    fun login(@RequestBody request: AuthRequest): ResponseEntity<AuthResponse> {
        val authToken = UsernamePasswordAuthenticationToken(request.username, request.password)
        authenticationManager.authenticate(authToken)

        val token = jwtService.generateToken(request.username)
        return ResponseEntity.ok(AuthResponse(token))
    }
}
