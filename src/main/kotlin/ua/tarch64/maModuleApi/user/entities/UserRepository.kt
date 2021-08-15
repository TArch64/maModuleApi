package ua.tarch64.maModuleApi.user.entities

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findUserByUsername(username: String): UserEntity?
}
