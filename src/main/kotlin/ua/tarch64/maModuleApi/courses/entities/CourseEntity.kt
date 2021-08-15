package ua.tarch64.maModuleApi.courses.entities

import ua.tarch64.maModuleApi.courses.enums.CourseTypes
import javax.persistence.*

@Entity
data class CourseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(unique = true)
    val name: String,

    @Enumerated
    val type: CourseTypes,

    @ManyToOne()
    @JoinColumn(name = "season_id")
    val season: CourseSeasonEntity
)
