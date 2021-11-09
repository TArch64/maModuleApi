package ua.tarch64.maModuleApi.auth.controllers

import org.springframework.web.bind.annotation.*
import ua.tarch64.maModuleApi.auth.AuthFacade
import ua.tarch64.maModuleApi.auth.controllers.requests.SignInRequest
import ua.tarch64.maModuleApi.auth.controllers.responses.AuthResponse

@RestController
@RequestMapping("/auth")
class AuthController(private val authFacade: AuthFacade) {
    @PostMapping("/sign-in")
    fun signIn(@RequestBody body: SignInRequest): AuthResponse {
        val token = authFacade.signIn(body.email, body.password)
        return AuthResponse(token)
    }
}
