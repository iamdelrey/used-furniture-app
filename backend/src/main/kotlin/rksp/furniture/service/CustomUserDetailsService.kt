package rksp.furniture.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import rksp.furniture.repository.UserRepository

class CustomUserDetailsService : UserDetailsService {

    private val userRepository = UserRepositoryHolder.repo

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("User not found") }

        return User(user.username, user.password, emptyList())
    }
}

object UserRepositoryHolder {
    lateinit var repo: rksp.furniture.repository.UserRepository
}
