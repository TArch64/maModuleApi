package ua.tarch64.maModuleApi.user.enums

enum class UserInvitationStatuses {
    PENDING,
    ACCEPTED;

    val isAccepted: Boolean get() = this === ACCEPTED
}
