package ua.tarch64.maModuleApi.admin.courses.controllers.responses

import ua.tarch64.maModuleApi.courses.entities.CourseMentorEntity
import ua.tarch64.maModuleApi.courses.enums.CourseMentorRoles
import java.util.*

data class CourseMentorResponse(
    val id: UUID,
    val role: CourseMentorRoles,
    val user: CourseMemberResponse
) {
    companion object {
        fun fromEntity(entity: CourseMentorEntity): CourseMentorResponse {
            return CourseMentorResponse(
                id = entity.id,
                role = entity.role,
                user = CourseMemberResponse.fromEntity(entity.user)
            )
        }
    }
}
