package ua.tarch64.maModuleApi.user.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.enums.UserRoles
import java.util.*

@Repository
interface UserRepository: JpaRepository<UserEntity, UUID> {
    fun findUserByEmail(email: String): UserEntity?
    fun findAllByRole(role: UserRoles): List<UserEntity>
}
