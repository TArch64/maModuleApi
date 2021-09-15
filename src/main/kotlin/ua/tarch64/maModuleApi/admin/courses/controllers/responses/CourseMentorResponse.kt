package ua.tarch64.maModuleApi.admin.courses.controllers.responses

import ua.tarch64.maModuleApi.courses.entities.CourseMentorEntity
import ua.tarch64.maModuleApi.courses.enums.CourseMentorRoles

data class CourseMentorResponse(
    val id: Long,
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
