package ua.tarch64.maModuleApi.user.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.enums.UserRoles
import java.util.*

@Repository
interface UserRepository: JpaRepository<UserEntity, UUID> {
    fun findUserByEmail(email: String): UserEntity?

    @Query("""
        SELECT u FROM UserEntity u
        WHERE u.role = :role AND LOWER(u.email) IN :emails
    """)
    fun findAllByRoleAndEmails(
        @Param("role") role: UserRoles,
        @Param("emails") emails: List<String>
    ): List<UserEntity>
}
