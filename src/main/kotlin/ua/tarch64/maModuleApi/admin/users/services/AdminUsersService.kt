package ua.tarch64.maModuleApi.admin.users.services

import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.common.errorHandling.exceptions.ValidationException
import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.enums.UserRoles
import ua.tarch64.maModuleApi.user.repositories.filters.SearchFilter
import ua.tarch64.maModuleApi.user.services.UsersService
import java.util.*

@Service
class AdminUsersService(private val usersService: UsersService) {
    fun getUserById(userId: UUID): UserEntity {
        return usersService.getUserById(userId) ?: throw ValidationException("User not found")
    }

    fun searchUsers(role: UserRoles, query: String, limit: Int): List<UserEntity> {
        val filter = SearchFilter(UserRoles.MENTOR, query, query, limit)
        return usersService.searchUsers(filter)
    }

    fun getByEmailsInRole(role: UserRoles, emails: List<String>): List<UserEntity> {
        return usersService.getByEmailsInRole(role, emails.map { it.lowercase() })
    }
}
