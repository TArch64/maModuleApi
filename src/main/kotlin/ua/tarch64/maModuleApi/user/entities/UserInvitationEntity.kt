package ua.tarch64.maModuleApi.user.entities

import org.hibernate.annotations.GenericGenerator
import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.user.enums.UserInvitationStatuses
import ua.tarch64.maModuleApi.user.enums.UserRoles
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user_invitations")
data class UserInvitationEntity(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    val id: UUID = UUID.randomUUID(),

    @Enumerated
    @Column(nullable = false)
    val role: UserRoles,

    @Column(nullable = false)
    val email: String,

    @Enumerated
    @Column(nullable = false)
    val status: UserInvitationStatuses = UserInvitationStatuses.PENDING,

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    val course: CourseEntity,
)
