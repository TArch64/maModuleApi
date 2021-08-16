package ua.tarch64.maModuleApi.auth

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import ua.tarch64.maModuleApi.auth.services.AuthTokenService
import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.enums.UserRoles
import ua.tarch64.maModuleApi.user.services.CurrentUserService
import ua.tarch64.maModuleApi.user.services.UsersService

@Component
class AuthFacade(
    private val authTokenService: AuthTokenService,
    private val authManager: AuthenticationManager,
    private val usersService: UsersService,
    private val currentUserService: CurrentUserService
) {
    fun signIn(username: String, password: String): String {
        val auth = authManager.authenticate(UsernamePasswordAuthenticationToken(username, password, emptyList()))
        SecurityContextHolder.getContext().authentication = auth
        return authTokenService.generateToken(auth.principal as User)
    }

    fun signUp(username: String, password: String, role: UserRoles): String {
        usersService.createUser(UserEntity.CreateOptions(role, username, password))
        return signIn(username, password)
    }

    fun fetchCurrentUser(): UserEntity {
        return currentUserService.fetchCurrentUser()
    }
}
