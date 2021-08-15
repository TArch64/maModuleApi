package ua.tarch64.maModuleApi.auth.controllers

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ua.tarch64.maModuleApi.auth.AuthFacade
import ua.tarch64.maModuleApi.auth.controllers.requests.SignInRequest
import ua.tarch64.maModuleApi.auth.controllers.requests.SignUpRequest
import ua.tarch64.maModuleApi.auth.controllers.responses.AuthResponse
import ua.tarch64.maModuleApi.user.services.UsersService

@RestController
@RequestMapping("/auth")
class AuthController(
    private val usersService: UsersService,
    private val authFacade: AuthFacade
) {
    @PostMapping("/sign-in")
    fun signIn(@RequestBody body: SignInRequest): AuthResponse {
        val token = authFacade.signIn(body.username, body.password)
        return AuthResponse(token)
    }

    @PostMapping("/sign-up")
    fun signUp(@RequestBody body: SignUpRequest): AuthResponse {
        val token = authFacade.signUp(body.username, body.password, body.role)
        return AuthResponse(token)
    }
}
