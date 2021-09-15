package ua.tarch64.maModuleApi.admin.courses.services

import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.common.errorHandling.exceptions.ValidationException
import ua.tarch64.maModuleApi.courses.entities.CourseSeasonEntity
import ua.tarch64.maModuleApi.courses.services.CourseSeasonsService
import java.time.Year

@Service
class CourseSeasonsAdminService(private val seasonsService: CourseSeasonsService) {
    fun getSeasons(): List<CourseSeasonEntity> {
        return seasonsService.getAll()
    }

    fun addSeason(makeActive: Boolean): CourseSeasonEntity {
        val savingSeasons = mutableListOf<CourseSeasonEntity>()

        if (makeActive) {
            seasonsService.getActive()?.run {
                savingSeasons.add(copy(active = false))
            }
        }

        val lastSeasonValue = seasonsService.getLast()?.value ?: 0
        savingSeasons.add(
            CourseSeasonEntity(
            value = lastSeasonValue + 1,
            active = makeActive,
            year = getCurrentYear()
        )
        )

        return seasonsService.save(savingSeasons).last()
    }

    fun removeSeason(seasonId: Long) {
        seasonsService.remove(getSeasonById(seasonId))
    }

    fun getSeasonById(seasonId: Long): CourseSeasonEntity {
        return seasonsService.getById(seasonId) ?: throw ValidationException("Season not found")
    }

    fun activateSeason(seasonId: Long) {
        val savingSeasons = mutableListOf<CourseSeasonEntity>()

        seasonsService.getActive()?.run {
            savingSeasons.add(copy(active = false))
        }

        savingSeasons.add(getSeasonById(seasonId).copy(active = true))
        seasonsService.save(savingSeasons)
    }

    fun deactivateSeason(seasonId: Long) {
        val season = getSeasonById(seasonId).copy(active = false)
        seasonsService.save(season)
    }

    private fun getCurrentYear(): Int = Year.now().value
}
