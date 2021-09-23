package ua.tarch64.maModuleApi.courses.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "seasons")
data class SeasonEntity(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    val id: UUID = UUID.randomUUID(),

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
