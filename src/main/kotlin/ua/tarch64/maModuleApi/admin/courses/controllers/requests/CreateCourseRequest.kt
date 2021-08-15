package ua.tarch64.maModuleApi.admin.courses.controllers.requests

import ua.tarch64.maModuleApi.courses.enums.CourseTypes

data class CreateCourseRequest(
    val name: String,
    val type: CourseTypes
)
