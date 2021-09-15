package ua.tarch64.maModuleApi.auth.controllers.requests

import javax.validation.constraints.NotBlank

data class SignInRequest(
    @NotBlank(message = "'username' can't be blank")
    val username: String,

    @NotBlank(message = "'password' can't be blank")
    val password: String
)
