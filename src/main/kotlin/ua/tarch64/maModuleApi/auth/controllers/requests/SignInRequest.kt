package ua.tarch64.maModuleApi.auth.controllers.requests

import ua.tarch64.maModuleApi.common.Constants
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class SignInRequest(
    @NotBlank(message = "'email' can't be blank")
    @Email(message = "Email is not correct", regexp = Constants.Regexps.EMAIL)
    val email: String,

    @NotBlank(message = "'password' can't be blank")
    val password: String
)
