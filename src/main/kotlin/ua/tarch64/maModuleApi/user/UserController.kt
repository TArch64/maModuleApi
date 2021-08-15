package ua.tarch64.maModuleApi.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.services.CurrentUserService

@RestController
@RequestMapping("/users")
class UserController(private val currentUserService: CurrentUserService) {
    @GetMapping("/current")
    fun getCurrentUser(): UserEntity {
        return currentUserService.fetchCurrentUser()
    }
}
