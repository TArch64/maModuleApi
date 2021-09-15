package ua.tarch64.maModuleApi.courses.entities

import ua.tarch64.maModuleApi.courses.enums.CourseTypes
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Enumerated
    @Column(nullable = false)
    val type: CourseTypes,

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    val season: CourseSeasonEntity,

    @OneToMany(
        targetEntity = CourseMentorEntity::class,
        mappedBy = "course",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY
    )
    val mentors: List<CourseMentorEntity> = emptyList()
)
