package ua.tarch64.maModuleApi.auth.controllers.requests

import ua.tarch64.maModuleApi.common.Constants
import ua.tarch64.maModuleApi.user.enums.UserRoles
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class SignUpRequest(
    @NotBlank(message = "'email' can't be blank")
    @Email(message = "Email is not correct", regexp = Constants.Regexps.EMAIL)
    val email: String,

    @NotBlank(message = "'username' can't be blank")
    val username: String,

    @NotBlank(message = "'password' can't be blank")
    val password: String,

    @NotBlank(message = "'role' can't be blank")
    val role: UserRoles
)
