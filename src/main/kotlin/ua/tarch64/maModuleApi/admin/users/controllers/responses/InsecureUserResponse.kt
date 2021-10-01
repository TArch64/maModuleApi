package ua.tarch64.maModuleApi.admin.users.controllers.responses

import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.enums.UserRoles
import java.util.*

data class InsecureUserResponse(
    val id: UUID,
    val email: String,
    val username: String,
    val role: UserRoles
) {
    companion object {
        fun fromEntity(entity: UserEntity): InsecureUserResponse {
            return InsecureUserResponse(
                id = entity.id,
                email = entity.email,
                username = entity.username,
                role = entity.role
            )
        }
    }
}
