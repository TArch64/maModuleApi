package ua.tarch64.maModuleApi.user.services

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.common.errorHandling.exceptions.ValidationException
import ua.tarch64.maModuleApi.common.helpers.asKOptional
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
        if (findUserByUsername(options.username) != null) {
            throw ValidationException("Username already exists")
        }

        val user = UserEntity(
            0,
            options.role,
            options.username,
            passwordEncoder.encode(options.password)
        )
        return repository.save(user)
    }
    fun getUserById(userId: Long): UserEntity? {
        return repository.findById(userId).asKOptional()
    }
}
