package ua.tarch64.maModuleApi.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ua.tarch64.maModuleApi.user.entities.UserEntity

@RestController
@RequestMapping("/users")
class UserController {
    @GetMapping("/current")
    fun getCurrentUser(): UserEntity {
        return UserEntity(1234)
    }
}
