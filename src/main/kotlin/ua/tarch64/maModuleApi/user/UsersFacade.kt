package ua.tarch64.maModuleApi.user

import org.springframework.stereotype.Component
import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.services.CurrentUserService

@Component
class UsersFacade(private val currentUserService: CurrentUserService) {
    fun fetchCurrentUser(): UserEntity {
        return this.currentUserService.fetchCurrentUser()
    }
}
