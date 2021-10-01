package ua.tarch64.maModuleApi.admin.users

import org.springframework.stereotype.Component
import ua.tarch64.maModuleApi.admin.users.services.AdminUsersService
import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.enums.UserRoles

@Component
class AdminUsersFacade(private val usersService: AdminUsersService) {
    fun searchMentors(query: String, limit: Int): List<UserEntity> {
        return usersService.searchUsers(UserRoles.MENTOR, query, limit)
    }
}
