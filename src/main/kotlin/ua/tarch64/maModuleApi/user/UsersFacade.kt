package ua.tarch64.maModuleApi.user

import org.springframework.stereotype.Component
import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.entities.UserInvitationEntity
import ua.tarch64.maModuleApi.user.enums.UserRoles
import ua.tarch64.maModuleApi.user.repositories.filters.SearchFilter
import ua.tarch64.maModuleApi.user.services.CurrentUserService
import ua.tarch64.maModuleApi.user.services.UserInvitationsService
import ua.tarch64.maModuleApi.user.services.UsersService
import java.util.*

@Component
class UsersFacade(
    private val currentUserService: CurrentUserService,
    private val userInvitationsService: UserInvitationsService
) {
    fun fetchCurrentUser(): UserEntity {
        return currentUserService.fetchCurrentUser()
    }

    fun findUserInvitations(invitationId: UUID): List<UserInvitationEntity> {
        return userInvitationsService.findUserInvitations(invitationId)
    }
}
