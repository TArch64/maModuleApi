package ua.tarch64.maModuleApi.courses.entities

import ua.tarch64.maModuleApi.courses.enums.CourseMentorRoles
import ua.tarch64.maModuleApi.user.entities.UserEntity
import javax.persistence.*

@Entity
@Table(name = "course_mentors")
data class CourseMentorEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

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
