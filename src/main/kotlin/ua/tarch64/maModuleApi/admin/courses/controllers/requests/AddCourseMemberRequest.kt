package ua.tarch64.maModuleApi.admin.courses.controllers.requests

import java.util.*
import javax.validation.constraints.NotNull

data class AddCourseMemberRequest(
    @NotNull(message = "'userId' can't be blank")
    val userId: UUID
)
