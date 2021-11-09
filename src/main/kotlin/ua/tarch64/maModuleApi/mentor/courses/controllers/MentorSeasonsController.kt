package ua.tarch64.maModuleApi.mentor.courses.controllers

import org.springframework.web.bind.annotation.*
import ua.tarch64.maModuleApi.admin.courses.controllers.responses.SeasonResponse
import ua.tarch64.maModuleApi.auth.annotations.RequireMentorRole

@RestController
@RequestMapping("/mentor/seasons")
@RequireMentorRole
class MentorSeasonsController {
    @GetMapping
    fun getSeasons(): List<SeasonResponse> {
        return emptyList()
    }
}
