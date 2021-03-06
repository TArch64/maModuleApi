package ua.tarch64.maModuleApi.auth.services

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.services.UsersService

@Service
class UserDetailsServiceImpl(
    private val usersService: UsersService
    ): UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = usersService.findUserByEmail(email) ?: throw UsernameNotFoundException(email)
        return User(user.email, user.password, buildGrantedAuthorities(user))
    }

    private fun buildGrantedAuthorities(user: UserEntity): List<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority(user.role.name))
    }
}
