package ua.tarch64.maModuleApi.user.controllers

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ua.tarch64.maModuleApi.user.UsersFacade
import ua.tarch64.maModuleApi.user.controllers.requests.AcceptInvitationRequest
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

    @PostMapping("/{id}/accept")
    fun accept(
        @PathVariable("id") id: UUID,
        @Validated @RequestBody body: AcceptInvitationRequest
    ) {
        facade.acceptInvitation(id, body.username, body.password)
    }
}
