package ua.tarch64.maModuleApi.admin.courses.controllers.responses

import ua.tarch64.maModuleApi.user.entities.UserEntity

data class CourseMemberResponse(
    val id: Long,
    val username: String
) {
    companion object {
        fun fromEntity(entity: UserEntity): CourseMemberResponse {
            return CourseMemberResponse(entity.id, entity.username)
        }
    }
}

