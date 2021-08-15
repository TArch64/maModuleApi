package ua.tarch64.maModuleApi.auth.controllers.requests

import ua.tarch64.maModuleApi.user.enums.UserRoles

data class SignUpRequest(
    val username: String,
    val password: String,
    val role: UserRoles
)
