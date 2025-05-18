package rksp.furniture

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import rksp.furniture.repository.UserRepository
import rksp.furniture.service.UserRepositoryHolder

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
open class UsedFurnitureApplication

fun main(args: Array<String>) {
    SpringApplication.run(UsedFurnitureApplication::class.java, *args)
}

@Component
class StartupInit(private val userRepository: UserRepository) {
    @PostConstruct
    fun init() {
        UserRepositoryHolder.repo = userRepository
    }
}