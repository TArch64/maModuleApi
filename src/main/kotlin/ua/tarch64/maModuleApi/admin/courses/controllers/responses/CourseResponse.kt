package ua.tarch64.maModuleApi.admin.courses.controllers.responses

import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.courses.enums.CourseTypes

data class CourseResponse(
    override val id: Long,
    override val name: String,
    override val type: CourseTypes
): ICourseResponse {
    companion object {
        fun fromEntity(entity: CourseEntity): CourseResponse {
            return CourseResponse(entity.id, entity.name, entity.type)
        }
    }
}

interface ICourseResponse {
    val id: Long
    val name: String
    val type: CourseTypes
}
