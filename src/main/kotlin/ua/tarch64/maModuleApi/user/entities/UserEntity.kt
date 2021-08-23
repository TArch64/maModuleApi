package ua.tarch64.maModuleApi.user.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import ua.tarch64.maModuleApi.user.enums.UserRoles
import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Enumerated
    val role: UserRoles,
    @Column(unique = true)
    val username: String,
    val password: String
) {
    data class CreateOptions(
        val role: UserRoles,
        val username: String,
        val password: String
    )
}
