package rksp.furniture.repository

import org.springframework.data.jpa.repository.JpaRepository
import rksp.furniture.model.User
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>
}
