package ua.tarch64.maModuleApi.auth.controllers

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.*
import ua.tarch64.maModuleApi.auth.AuthFacade
import ua.tarch64.maModuleApi.auth.controllers.requests.SignInRequest
import ua.tarch64.maModuleApi.auth.controllers.requests.SignUpRequest
import ua.tarch64.maModuleApi.auth.controllers.responses.AuthResponse
import ua.tarch64.maModuleApi.common.errorHandling.exceptions.ValidationException

@RestController
@RequestMapping("/auth")
class AuthController(private val authFacade: AuthFacade) {
    @PostMapping("/sign-in")
    fun signIn(@RequestBody body: SignInRequest): AuthResponse {
        try {
            val token = authFacade.signIn(body.username, body.password)
            return AuthResponse(token)
        } catch (exception: BadCredentialsException) {
            throw ValidationException("Username or password is not correct")
        }
    }

    @PostMapping("/sign-up")
    fun signUp(@RequestBody body: SignUpRequest): AuthResponse {
        val token = authFacade.signUp(body.username, body.password, body.role)
        return AuthResponse(token)
    }
}
