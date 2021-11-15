package ua.tarch64.maModuleApi.user.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ua.tarch64.maModuleApi.common.services.UrlService
import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.mailer.entities.EmailDraft
import ua.tarch64.maModuleApi.mailer.entities.EmailTemplate
import ua.tarch64.maModuleApi.mailer.services.EmailSender
import ua.tarch64.maModuleApi.user.entities.UserInvitationEntity
import ua.tarch64.maModuleApi.user.enums.UserRoles
import ua.tarch64.maModuleApi.user.repositories.UserInvitationRepository
import java.util.*

@Service
class UserInvitationsService(
    private val repository: UserInvitationRepository,
    private val urlService: UrlService,
    private val emailSender: EmailSender
) {
    fun invite(role: UserRoles, emails: List<String>, course: CourseEntity) {
        val invitations = emails.map {
            UserInvitationEntity(email = it, role = role, course = course)
        }
        sendInvites(saveAll(invitations))
    }

    private fun sendInvites(invitations: List<UserInvitationEntity>) {
        val template = EmailTemplate(
            name = "invite-user",
            images = listOf("ma-hero-image.jpg", "ma-logo-black.jpg")
        )

        emailSender.send(invitations.map {
            EmailDraft(
                to = it.email,
                subject = "Welcome to MA Community!",
                template = template.usePayload(
                    "courseName" to it.course.name,
                    "recipientRole" to it.role.title,
                    "joinPath" to urlService.buildJoinUrl(it.id)
                )
            )
        })
    }

    @Transactional
    fun saveAll(entities: List<UserInvitationEntity>): List<UserInvitationEntity> {
        return repository.saveAll(entities)
    }

    fun findUserInvitations(invitationId: UUID): List<UserInvitationEntity> {
        return repository.findAllByOneId(invitationId)
    }

    @Transactional
    fun acceptInvitations(invitationId: UUID): List<UserInvitationEntity> {
        return findUserInvitations(invitationId).map { it.accepted() }.also { saveAll(it) }
    }
}
