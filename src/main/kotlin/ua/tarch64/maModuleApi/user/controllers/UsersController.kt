package ua.tarch64.maModuleApi.user.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ua.tarch64.maModuleApi.user.UsersFacade
import ua.tarch64.maModuleApi.user.controllers.responses.UserResponse

@RestController
@RequestMapping("/users")
class UsersController(private val usersFacade: UsersFacade) {
    @GetMapping("/current")
    fun getCurrentUser(): UserResponse {
        val user = usersFacade.fetchCurrentUser()
        return UserResponse(user.id, user.username, user.role)
    }
}
