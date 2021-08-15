package ua.tarch64.maModuleApi.user.services

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.user.entities.UserEntity

@Service
class CurrentUserService(
    private val usersService: UsersService
) {
    fun fetchCurrentUser(): UserEntity {
        val authentication = SecurityContextHolder.getContext().authentication
        return usersService.findUserByUsername(authentication.principal as String)!!
    }
}
