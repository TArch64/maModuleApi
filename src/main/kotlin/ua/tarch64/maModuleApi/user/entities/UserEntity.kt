package ua.tarch64.maModuleApi.user.entities

import org.hibernate.annotations.GenericGenerator
import ua.tarch64.maModuleApi.user.enums.UserRoles
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
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

    @Column(unique = true, nullable = false)
    val username: String,

    @Column(nullable = false)
    val password: String
) {
    data class CreateOptions(
        val role: UserRoles,
        val username: String,
        val password: String
    )
}
