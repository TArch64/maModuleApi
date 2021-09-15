package ua.tarch64.maModuleApi.admin.courses.controllers.responses

import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.courses.enums.CourseTypes

data class FullCourseResponse(
    override val id: Long,
    override val name: String,
    override val type: CourseTypes,
    val mentors: List<CourseMentorResponse>
): ICourseResponse {
    companion object {
        fun fromEntity(entity: CourseEntity): FullCourseResponse {
            return FullCourseResponse(
                entity.id,
                entity.name,
                entity.type,
                entity.mentors.map(CourseMentorResponse::fromEntity)
            )
        }
    }
}
