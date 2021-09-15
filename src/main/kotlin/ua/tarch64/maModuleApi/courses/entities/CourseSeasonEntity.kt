package ua.tarch64.maModuleApi.courses.entities

import javax.persistence.*

@Entity
@Table(name = "course_seasons")
data class CourseSeasonEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @Column(unique = true, nullable = false)
    val value: Int,

    @Column(nullable = false)
    val active: Boolean,

    @Column(nullable = false)
    val year: Int,

    @OneToMany(
        targetEntity = CourseEntity::class,
        mappedBy = "season",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY
    )
    val courses: List<CourseEntity> = emptyList()
)
