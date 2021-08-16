package ua.tarch64.maModuleApi.auth.controllers.responses

import ua.tarch64.maModuleApi.user.controllers.responses.UserResponse

data class AuthResponse(
    val token: String,
    val user: UserResponse
)
