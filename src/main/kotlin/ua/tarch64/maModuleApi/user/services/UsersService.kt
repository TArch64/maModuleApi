package ua.tarch64.maModuleApi.user.services

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.entities.UserRepository

@Service
class UsersService(
    private val repository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun findUserByUsername(username: String): UserEntity? {
        return repository.findUserByUsername(username)
    }

    fun createUser(options: UserEntity.CreateOptions): UserEntity {
        val user = UserEntity(
            0,
            options.role,
            options.username,
            passwordEncoder.encode(options.password)
        )
        return repository.save(user)
    }
}
