package ua.tarch64.maModuleApi.user.enums

enum class UserRoles(val title: String) {
    MENTOR("Mentor"),
    STUDENT("Student"),
    ADMIN("Admin");

    val isMentor: Boolean get() = this === MENTOR
    val isStudent: Boolean get() = this === STUDENT
}
