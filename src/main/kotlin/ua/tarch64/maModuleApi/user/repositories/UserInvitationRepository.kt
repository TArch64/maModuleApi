package ua.tarch64.maModuleApi.user.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ua.tarch64.maModuleApi.user.entities.UserInvitationEntity
import java.util.*

@Repository
interface UserInvitationRepository: JpaRepository<UserInvitationEntity, UUID> {
    @Query("""
        SELECT * FROM user_invitations AS ui
        WHERE ui.email = (
            SELECT uie.email FROM user_invitations as uie
            WHERE uie.id = :oneId
            LIMIT 1
        )
    """, nativeQuery = true)
    fun findAllByOneId(@Param("oneId") invitationId: UUID): List<UserInvitationEntity>
}
