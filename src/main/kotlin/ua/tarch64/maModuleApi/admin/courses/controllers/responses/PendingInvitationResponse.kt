package ua.tarch64.maModuleApi.admin.courses.controllers.responses

import ua.tarch64.maModuleApi.user.entities.UserInvitationEntity
import java.util.*

data class PendingCourseInvitationResponse(
    val id: UUID,
    val email: String
) {
    companion object {
        fun fromEntity(entity: UserInvitationEntity): PendingCourseInvitationResponse {
            return PendingCourseInvitationResponse(entity.id, entity.email)
        }
    }
}
