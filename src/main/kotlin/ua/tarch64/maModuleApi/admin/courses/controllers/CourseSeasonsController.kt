package ua.tarch64.maModuleApi.admin.courses.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ua.tarch64.maModuleApi.admin.courses.CoursesFacade
import ua.tarch64.maModuleApi.admin.courses.controllers.responses.CourseSeasonResponse
import ua.tarch64.maModuleApi.auth.annotations.RequireAdminRole

@RestController
@RequestMapping("/admin/course-seasons")
@RequireAdminRole
class CourseSeasonsController(private val coursesFacade: CoursesFacade) {
    @GetMapping
    fun getSeasons(): List<CourseSeasonResponse> {
        val seasons = coursesFacade.getSeasons()
        return seasons.map(CourseSeasonResponse::fromEntity)
    }

    @PostMapping("/active")
    fun addSeason(): CourseSeasonResponse {
        return CourseSeasonResponse.fromEntity(coursesFacade.addActiveSeason())
    }
}
