package ua.tarch64.maModuleApi.admin.courses.controllers

import org.springframework.web.bind.annotation.*
import ua.tarch64.maModuleApi.admin.courses.CoursesFacade
import ua.tarch64.maModuleApi.admin.courses.controllers.requests.AddSeasonRequest
import ua.tarch64.maModuleApi.admin.courses.controllers.requests.ToggleActiveSeasonRequest
import ua.tarch64.maModuleApi.admin.courses.controllers.responses.CourseSeasonResponse
import ua.tarch64.maModuleApi.auth.annotations.RequireAdminRole
import javax.validation.Valid

@RestController
@RequestMapping("/admin/course-seasons")
@RequireAdminRole
class CourseSeasonsController(private val coursesFacade: CoursesFacade) {
    @GetMapping
    fun getSeasons(): List<CourseSeasonResponse> {
        val seasons = coursesFacade.getSeasons()
        return seasons.map(CourseSeasonResponse::fromEntity)
    }

    @PostMapping
    fun addSeason(@Valid @RequestBody body: AddSeasonRequest): CourseSeasonResponse {
        return CourseSeasonResponse.fromEntity(coursesFacade.addSeason(body.makeActive))
    }

    @DeleteMapping("/{season_id}")
    fun removeSeason(@PathVariable("season_id") seasonId: Long) {
        coursesFacade.removeSeason(seasonId)
    }

    @PostMapping("/activate")
    fun activateSeason(@Valid @RequestBody body: ToggleActiveSeasonRequest) {
        coursesFacade.activateSeason(body.seasonId)
    }

    @PostMapping("/deactivate")
    fun deactivateSeason(@Valid @RequestBody body: ToggleActiveSeasonRequest) {
        coursesFacade.deactivateSeason(body.seasonId)
    }
}
