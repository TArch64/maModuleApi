package ua.tarch64.maModuleApi.admin.courses.controllers.requests

import ua.tarch64.maModuleApi.courses.enums.CourseTypes
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class AddCourseRequest(
    @NotBlank(message = "'name' can't be blank")
    val name: String,

    @NotNull(message = "'type' can't be blank")
    val type: CourseTypes
)
