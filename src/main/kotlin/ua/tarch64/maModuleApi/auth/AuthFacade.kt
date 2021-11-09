package ua.tarch64.maModuleApi.auth

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import ua.tarch64.maModuleApi.auth.services.AuthTokenService
import ua.tarch64.maModuleApi.common.errorHandling.exceptions.ValidationException

@Component
class AuthFacade(
    private val authTokenService: AuthTokenService,
    private val authManager: AuthenticationManager
) {
    fun signIn(email: String, password: String): String {
        try {
            val auth = authManager.authenticate(UsernamePasswordAuthenticationToken(email, password, emptyList()))
            SecurityContextHolder.getContext().authentication = auth
            return authTokenService.generateToken(auth.principal as User)
        } catch (exception: BadCredentialsException) {
            throw ValidationException("Email or password is not correct")
        }
    }
}
