package ua.tarch64.maModuleApi.auth.controllers

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.*
import ua.tarch64.maModuleApi.auth.AuthFacade
import ua.tarch64.maModuleApi.auth.controllers.requests.SignInRequest
import ua.tarch64.maModuleApi.auth.controllers.requests.SignUpRequest
import ua.tarch64.maModuleApi.auth.controllers.responses.AuthResponse
import ua.tarch64.maModuleApi.common.factories.ErrorResponseFactory
import ua.tarch64.maModuleApi.common.responses.ErrorResponse

@RestController
@RequestMapping("/auth")
@ControllerAdvice
class AuthController(
    private val authFacade: AuthFacade,
    private val errorResponseFactory: ErrorResponseFactory
) {
    @PostMapping("/sign-in")
    fun signIn(@RequestBody body: SignInRequest): AuthResponse {
        val token = authFacade.signIn(body.username, body.password)
        return AuthResponse(token)
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun onBadCredentialsError(): ResponseEntity<ErrorResponse> {
        return errorResponseFactory.createAuthResponse("Username or password is not correct")
    }

    @PostMapping("/sign-up")
    fun signUp(@RequestBody body: SignUpRequest): AuthResponse {
        val token = authFacade.signUp(body.username, body.password, body.role)
        return AuthResponse(token)
    }
}
