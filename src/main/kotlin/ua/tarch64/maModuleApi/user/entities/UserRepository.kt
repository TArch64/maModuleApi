package ua.tarch64.maModuleApi.user.entities

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<UserEntity, UUID> {
    fun findUserByUsername(username: String): UserEntity?
}
