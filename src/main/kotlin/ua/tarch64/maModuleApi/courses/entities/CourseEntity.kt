package ua.tarch64.maModuleApi.courses.entities

import org.hibernate.annotations.GenericGenerator
import ua.tarch64.maModuleApi.courses.enums.CourseTypes
import ua.tarch64.maModuleApi.user.entities.UserInvitationEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(
    name = "courses",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["season_id", "name"])
    ]
)
data class CourseEntity(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val name: String,

    @Enumerated
    @Column(nullable = false)
    val type: CourseTypes,

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    val season: SeasonEntity,

    @OneToMany(
        targetEntity = CourseMentorEntity::class,
        mappedBy = "course",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY
    )
    val mentors: List<CourseMentorEntity> = emptyList(),

    @OneToMany(
        targetEntity = UserInvitationEntity::class,
        mappedBy = "course",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY
    )
    val invitations: List<UserInvitationEntity> = emptyList()
)
