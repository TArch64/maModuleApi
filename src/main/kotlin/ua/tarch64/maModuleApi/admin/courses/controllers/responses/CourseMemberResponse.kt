package ua.tarch64.maModuleApi.admin.courses.controllers.responses

import ua.tarch64.maModuleApi.user.entities.UserEntity
import java.util.*

data class CourseMemberResponse(
    val id: UUID,
    val username: String
) {
    companion object {
        fun fromEntity(entity: UserEntity): CourseMemberResponse {
            return CourseMemberResponse(entity.id, entity.username)
        }
    }
}

