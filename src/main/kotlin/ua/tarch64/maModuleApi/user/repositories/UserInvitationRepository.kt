package ua.tarch64.maModuleApi.user.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ua.tarch64.maModuleApi.user.entities.UserInvitationEntity
import java.util.*

@Repository
interface UserInvitationRepository: JpaRepository<UserInvitationEntity, UUID>
