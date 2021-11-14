package ua.tarch64.maModuleApi.admin.users.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ua.tarch64.maModuleApi.admin.users.AdminUsersFacade
import ua.tarch64.maModuleApi.admin.users.controllers.responses.InsecureUserResponse
import ua.tarch64.maModuleApi.auth.annotations.RequireAdminRole

@RestController
@RequireAdminRole
@RequestMapping("/admin/users")
class AdminUsersController(private val facade: AdminUsersFacade) {
    @GetMapping("/mentors")
    fun searchMentors(
        @RequestParam("query") query: String,
        @RequestParam("limit") limit: Int
    ): List<InsecureUserResponse> {
        return facade.searchMentors(query, limit).map(InsecureUserResponse::fromEntity)
    }

    @GetMapping("/students")
    fun searchStudents(
        @RequestParam("query") query: String,
        @RequestParam("limit") limit: Int
    ): List<InsecureUserResponse> {
        return facade.searchStudents(query, limit).map(InsecureUserResponse::fromEntity)
    }
}
