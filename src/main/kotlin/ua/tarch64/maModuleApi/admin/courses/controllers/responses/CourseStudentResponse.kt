package ua.tarch64.maModuleApi.admin.courses.controllers.responses

import ua.tarch64.maModuleApi.courses.entities.CourseStudentEntity
import java.util.*

data class CourseStudentResponse(
    val id: UUID,
    val user: CourseMemberResponse
) {
    companion object {
        fun fromEntity(entity: CourseStudentEntity): CourseStudentResponse {
            return CourseStudentResponse(
                id = entity.id,
                user = CourseMemberResponse.fromEntity(entity.user)
            )
        }
    }
}
