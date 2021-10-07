package ua.tarch64.maModuleApi.admin.courses.controllers.requests

import java.util.*
import javax.validation.constraints.NotNull

data class ChangeLeadMentorRequest(
    @NotNull(message = "'mentorId' can't be blank")
    val mentorId: UUID
)
