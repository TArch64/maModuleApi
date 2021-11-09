package ua.tarch64.maModuleApi.user.controllers.responses

import ua.tarch64.maModuleApi.user.entities.UserInvitationEntity
import ua.tarch64.maModuleApi.user.enums.UserInvitationStatuses

data class UserInvitationsResponse(
    val status: UserInvitationStatuses,
    val email: String,
    val courseNames: List<String>
) {
    companion object {
        fun fromEntities(entities: List<UserInvitationEntity>): UserInvitationsResponse {
            val first = entities.first()

            if (first.isAccepted) return createAccepted()

            return UserInvitationsResponse(
                status = first.status,
                email = first.email,
                entities.map { it.course.name }.distinct()
            )
        }

        private fun createAccepted(): UserInvitationsResponse {
            return UserInvitationsResponse(
                status = UserInvitationStatuses.ACCEPTED,
                email = "",
                courseNames = emptyList()
            )
        }
    }
}
