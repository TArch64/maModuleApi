package ua.tarch64.maModuleApi.courses.entities

import org.hibernate.annotations.GenericGenerator
import ua.tarch64.maModuleApi.user.entities.UserEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "course_students")
data class CourseStudentEntity(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity,

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    val course: CourseEntity
)
