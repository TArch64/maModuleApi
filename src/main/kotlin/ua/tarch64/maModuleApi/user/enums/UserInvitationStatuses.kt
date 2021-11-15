package ua.tarch64.maModuleApi.user.enums

enum class UserInvitationStatuses {
    PENDING,
    ACCEPTED,
    REVOKED;

    val isPending: Boolean get() = this == PENDING
    val isAccepted: Boolean get() = this == ACCEPTED
    val isRevoked: Boolean get() = this == REVOKED
}
