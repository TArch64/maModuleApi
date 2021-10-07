package ua.tarch64.maModuleApi.user.services

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.common.errorHandling.exceptions.ValidationException
import ua.tarch64.maModuleApi.common.helpers.asKOptional
import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.enums.UserRoles
import ua.tarch64.maModuleApi.user.repositories.UserRepository
import ua.tarch64.maModuleApi.user.repositories.filters.SearchFilter
import java.util.*
import javax.persistence.EntityManager

@Service
class UsersService(
    private val repository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val entityManager: EntityManager
) {
    fun findUserByEmail(email: String): UserEntity? {
        return repository.findUserByEmail(email)
    }

    fun createUser(options: UserEntity.CreateOptions): UserEntity {
        if (findUserByEmail(options.email) != null) {
            throw ValidationException("User with this email already exists")
        }
        return repository.save(UserEntity(
            role = options.role,
            username = options.username,
            email = options.email,
            password = passwordEncoder.encode(options.password)
        ))
    }

    fun getUserById(userId: UUID): UserEntity? {
        return repository.findById(userId).asKOptional()
    }

    fun searchUsers(filter: SearchFilter): List<UserEntity> {
        return filter.createQuery(entityManager).resultList
    }

    fun getByEmailsInRole(role: UserRoles, emails: List<String>): List<UserEntity> {
        return repository.findAllByRoleAndEmails(role, emails)
    }
}
