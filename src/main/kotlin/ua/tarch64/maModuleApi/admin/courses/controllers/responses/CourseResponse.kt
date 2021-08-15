package ua.tarch64.maModuleApi.admin.courses.controllers.responses

import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.courses.enums.CourseTypes

data class CourseResponse(
    val id: Long,
    val name: String,
    val type: CourseTypes
) {
    companion object {
        fun fromEntity(entity: CourseEntity): CourseResponse {
            return CourseResponse(entity.id, entity.name, entity.type)
        }
    }
}
