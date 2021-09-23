package ua.tarch64.maModuleApi.courses.entities

import org.hibernate.annotations.GenericGenerator
import ua.tarch64.maModuleApi.courses.enums.CourseMentorRoles
import ua.tarch64.maModuleApi.user.entities.UserEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "course_mentors")
data class CourseMentorEntity(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    val id: UUID = UUID.randomUUID(),

    @Enumerated
    @Column(nullable = false)
    val role: CourseMentorRoles = CourseMentorRoles.MENTOR,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity,

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    val course: CourseEntity
)
