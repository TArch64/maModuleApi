package ua.tarch64.maModuleApi.user.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ua.tarch64.maModuleApi.user.UsersFacade
import ua.tarch64.maModuleApi.user.controllers.responses.UserInvitationsResponse
import java.util.*

@RestController
@RequestMapping("/invitations")
class UserInvitationsController(private val facade: UsersFacade) {
    @GetMapping("/{id}/all")
    fun getAll(@PathVariable("id") id: UUID): UserInvitationsResponse? {
        val invitations = facade.findUserInvitations(id)
        if (invitations.isEmpty()) return null
        return UserInvitationsResponse.fromEntities(invitations)
    }
}
