package ua.tarch64.maModuleApi.admin.courses.controllers.responses

import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.courses.enums.CourseTypes
import ua.tarch64.maModuleApi.user.enums.UserRoles
import java.util.*

data class FullCourseResponse(
    override val id: UUID,
    override val name: String,
    override val type: CourseTypes,
    val mentors: List<CourseMentorResponse>,
    val pendingMentorInvitations: List<PendingCourseInvitationResponse>,
    val students: List<CourseStudentResponse>,
    val pendingStudentInvitations: List<PendingCourseInvitationResponse>
): ICourseResponse {
    companion object {
        fun fromEntity(entity: CourseEntity): FullCourseResponse {
            return FullCourseResponse(
                entity.id,
                entity.name,
                entity.type,
                entity.mentors.map(CourseMentorResponse::fromEntity),
                pendingInvitationsByRole(entity, UserRoles.MENTOR),
                entity.students.map(CourseStudentResponse::fromEntity),
                pendingInvitationsByRole(entity, UserRoles.STUDENT)
            )
        }

        private fun pendingInvitationsByRole(entity: CourseEntity, role: UserRoles): List<PendingCourseInvitationResponse> {
            return entity.invitations
                .filter { it.status.isPending && it.role == role }
                .map(PendingCourseInvitationResponse::fromEntity)
        }
    }
}
