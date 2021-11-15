package ua.tarch64.maModuleApi.user

import org.springframework.stereotype.Component
import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.entities.UserInvitationEntity
import ua.tarch64.maModuleApi.user.services.CurrentUserService
import ua.tarch64.maModuleApi.user.services.UserCoursesService
import ua.tarch64.maModuleApi.user.services.UserInvitationsService
import ua.tarch64.maModuleApi.user.services.UsersService
import java.util.*

@Component
class UsersFacade(
    private val currentUserService: CurrentUserService,
    private val usersService: UsersService,
    private val userInvitationsService: UserInvitationsService,
    private val userCoursesService: UserCoursesService
) {
    fun fetchCurrentUser(): UserEntity {
        return currentUserService.fetchCurrentUser()
    }

    fun findUserInvitations(invitationId: UUID): List<UserInvitationEntity> {
        return userInvitationsService.findUserInvitations(invitationId)
    }

    fun acceptInvitation(invitationId: UUID, username: String, password: String) {
        val invitations = userInvitationsService.acceptInvitations(invitationId).distinctBy { it.id }
        val user = usersService.createUser(UserEntity.CreateOptions(
            role = invitations.first().role,
            email = invitations.first().email,
            username = username,
            password = password
        ))
        userCoursesService.addUserToCourses(user, invitations)
    }
}
