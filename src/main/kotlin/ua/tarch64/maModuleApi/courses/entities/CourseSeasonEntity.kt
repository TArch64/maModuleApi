package ua.tarch64.maModuleApi.courses.entities

import javax.persistence.*

@Entity
@Table(name = "course_seasons")
data class CourseSeasonEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(unique = true)
    val value: Int,

    @Column(unique = true)
    var active: Boolean,

    @Column(unique = true)
    val year: Int,

    @OneToMany(
        targetEntity = CourseEntity::class,
        mappedBy = "season",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY
    )
    val courses: List<CourseEntity>
)
