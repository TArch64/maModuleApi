package ua.tarch64.maModuleApi.user.controllers.responses

import ua.tarch64.maModuleApi.user.enums.UserRoles

data class UserResponse(
    val id: Long,
    val username: String,
    val role: UserRoles
)
