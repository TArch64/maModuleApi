package ua.tarch64.maModuleApi.admin.courses.controllers

import org.springframework.web.bind.annotation.*
import ua.tarch64.maModuleApi.admin.courses.CoursesFacade
import ua.tarch64.maModuleApi.admin.courses.controllers.requests.AddSeasonRequest
import ua.tarch64.maModuleApi.admin.courses.controllers.requests.ToggleActiveSeasonRequest
import ua.tarch64.maModuleApi.admin.courses.controllers.responses.SeasonResponse
import ua.tarch64.maModuleApi.auth.annotations.RequireAdminRole
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/admin/seasons")
@RequireAdminRole
class SeasonsController(private val coursesFacade: CoursesFacade) {
    @GetMapping
    fun getSeasons(): List<SeasonResponse> {
        val seasons = coursesFacade.getSeasons()
        return seasons.map(SeasonResponse::fromEntity)
    }

    @PostMapping
    fun addSeason(@Valid @RequestBody body: AddSeasonRequest): SeasonResponse {
        return SeasonResponse.fromEntity(coursesFacade.addSeason(body.makeActive))
    }

    @DeleteMapping("/{season_id}")
    fun removeSeason(@PathVariable("season_id") seasonId: UUID) {
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
