package ua.tarch64.maModuleApi.admin.courses.controllers.responses

import ua.tarch64.maModuleApi.courses.entities.CourseSeasonEntity

data class CourseSeasonResponse(
    val id: Long,
    val value: Int,
    val year: Int
) {
    companion object {
        fun fromEntity(entity: CourseSeasonEntity): CourseSeasonResponse {
            return CourseSeasonResponse(entity.id, entity.value, entity.year)
        }
    }
}
