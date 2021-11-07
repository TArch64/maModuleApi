package ua.tarch64.maModuleApi.user.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ua.tarch64.maModuleApi.user.entities.UserInvitationEntity
import ua.tarch64.maModuleApi.user.enums.UserRoles
import ua.tarch64.maModuleApi.user.repositories.UserInvitationRepository
import java.util.*

@Service
class UserInvitationsService(private val repository: UserInvitationRepository) {
    fun inviteMentors(emails: List<String>, courseId: UUID) {
        val invitations = emails.map {
            UserInvitationEntity(
                email = it,
                role = UserRoles.MENTOR,
                targetId = courseId
            )
        }
        saveAll(invitations)
    }

    @Transactional
    fun saveAll(entities: List<UserInvitationEntity>): List<UserInvitationEntity> {
        return repository.saveAll(entities)
    }
}
