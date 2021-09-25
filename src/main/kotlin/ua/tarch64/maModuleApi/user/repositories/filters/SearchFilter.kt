package ua.tarch64.maModuleApi.user.repositories.filters

import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.enums.UserRoles
import javax.persistence.EntityManager
import javax.persistence.TypedQuery

data class SearchFilter(
    val role: UserRoles,
    val username: String,
    val limit: Int
) {
    fun createQuery(entityManager: EntityManager): TypedQuery<UserEntity> {
        return entityManager.createQuery(prepareQueryString(), UserEntity::class.java).apply {
            setParameter("role", role)
            setParameter("username", prepareUsername())
            maxResults = limit
        }
    }

    private fun prepareQueryString(): String {
        return """
            SELECT u FROM UserEntity u
            WHERE u.role = :role AND LOWER(u.username) like :username
        """.trimIndent()
    }

    private fun prepareUsername(): String {
        return "%${username.lowercase()}%"
    }
}
