package ua.tarch64.maModuleApi.admin.courses.services

import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.common.errorHandling.exceptions.ValidationException
import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.services.UsersService
import java.util.*

@Service
class UsersAdminService(private val usersService: UsersService) {
    fun getUserById(userId: UUID): UserEntity {
        return usersService.getUserById(userId) ?: throw ValidationException("User not found")
    }
}
