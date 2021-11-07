package ua.tarch64.maModuleApi.user.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ua.tarch64.maModuleApi.common.services.UrlService
import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.mailer.entities.EmailDraftBatch
import ua.tarch64.maModuleApi.mailer.services.EmailSender
import ua.tarch64.maModuleApi.user.entities.UserInvitationEntity
import ua.tarch64.maModuleApi.user.enums.UserRoles
import ua.tarch64.maModuleApi.user.repositories.UserInvitationRepository

@Service
class UserInvitationsService(
    private val repository: UserInvitationRepository,
    private val urlService: UrlService,
    private val emailSender: EmailSender
) {
    fun inviteMentors(emails: List<String>, course: CourseEntity) {
        val invitations = emails.map {
            UserInvitationEntity(
                email = it,
                role = UserRoles.MENTOR,
                course = course
            )
        }
        sendInvites(saveAll(invitations))
    }

    private fun sendInvites(invitations: List<UserInvitationEntity>) {
        val draftItems = invitations.map {
            EmailDraftBatch.Item(it.email, hashMapOf(
                "courseName" to it.course.name,
                "recipientRole" to it.role.title,
                "joinPath" to urlService.buildJoinUrl(it.id)
            ))
        }
        emailSender.send(EmailDraftBatch(
            subject = "Welcome to MA Module!",
            template = "invite-user",
            items = draftItems
        ))
    }

    @Transactional
    fun saveAll(entities: List<UserInvitationEntity>): List<UserInvitationEntity> {
        return repository.saveAll(entities)
    }
}
