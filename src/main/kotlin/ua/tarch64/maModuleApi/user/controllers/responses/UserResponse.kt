package ua.tarch64.maModuleApi.user.controllers.responses

import ua.tarch64.maModuleApi.user.enums.UserRoles
import java.util.*

data class UserResponse(
    val id: UUID,
    val username: String,
    val role: UserRoles
)
