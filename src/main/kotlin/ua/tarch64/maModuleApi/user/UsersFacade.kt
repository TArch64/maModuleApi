package ua.tarch64.maModuleApi.user

import org.springframework.stereotype.Component
import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.enums.UserRoles
import ua.tarch64.maModuleApi.user.repositories.filters.SearchFilter
import ua.tarch64.maModuleApi.user.services.CurrentUserService
import ua.tarch64.maModuleApi.user.services.UsersService

@Component
class UsersFacade(
    private val currentUserService: CurrentUserService,
    private val usersService: UsersService
) {
    fun fetchCurrentUser(): UserEntity {
        return currentUserService.fetchCurrentUser()
    }

    fun searchMentors(query: String, limit: Int): List<UserEntity> {
        return usersService.searchUsers(SearchFilter(
            role = UserRoles.MENTOR,
            username = query,
            email = query,
            limit = limit
        ))
    }
}
