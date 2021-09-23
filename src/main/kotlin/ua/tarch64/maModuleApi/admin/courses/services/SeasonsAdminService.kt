package ua.tarch64.maModuleApi.admin.courses.services

import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.common.errorHandling.exceptions.ValidationException
import ua.tarch64.maModuleApi.courses.entities.SeasonEntity
import ua.tarch64.maModuleApi.courses.services.SeasonsService
import java.time.Year
import java.util.*

@Service
class SeasonsAdminService(private val seasonsService: SeasonsService) {
    fun getSeasons(): List<SeasonEntity> {
        return seasonsService.getAll()
    }

    fun addSeason(makeActive: Boolean): SeasonEntity {
        val savingSeasons = mutableListOf<SeasonEntity>()

        if (makeActive) {
            seasonsService.getActive()?.run {
                savingSeasons.add(copy(active = false))
            }
        }

        val lastSeasonValue = seasonsService.getLast()?.value ?: 0
        savingSeasons.add(
            SeasonEntity(
                value = lastSeasonValue + 1,
                active = makeActive,
                year = getCurrentYear()
            )
        )

        return seasonsService.save(savingSeasons).last()
    }

    fun removeSeason(seasonId: UUID) {
        seasonsService.remove(getSeasonById(seasonId))
    }

    fun getSeasonById(seasonId: UUID): SeasonEntity {
        return seasonsService.getById(seasonId) ?: throw ValidationException("Season not found")
    }

    fun activateSeason(seasonId: UUID) {
        val savingSeasons = mutableListOf<SeasonEntity>()

        seasonsService.getActive()?.run {
            savingSeasons.add(copy(active = false))
        }

        savingSeasons.add(getSeasonById(seasonId).copy(active = true))
        seasonsService.save(savingSeasons)
    }

    fun deactivateSeason(seasonId: UUID) {
        val season = getSeasonById(seasonId).copy(active = false)
        seasonsService.save(season)
    }

    private fun getCurrentYear(): Int = Year.now().value
}
