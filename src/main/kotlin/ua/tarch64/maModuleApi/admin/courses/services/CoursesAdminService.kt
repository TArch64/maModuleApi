package ua.tarch64.maModuleApi.admin.courses.services

import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.common.errorHandling.exceptions.ValidationException
import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.courses.entities.CourseSeasonEntity
import ua.tarch64.maModuleApi.courses.enums.CourseTypes
import ua.tarch64.maModuleApi.courses.services.CoursesService
import java.time.Year

@Service
class CoursesAdminService(private val coursesService: CoursesService) {
    fun getSeasonCourses(seasonId: Long): List<CourseEntity> {
        return coursesService.getSeasonCourses(seasonId)
    }

    fun getSeasons(): List<CourseSeasonEntity> {
        return coursesService.getSeasons()
    }

    fun addSeason(makeActive: Boolean): CourseSeasonEntity {
        val savingSeasons = mutableListOf<CourseSeasonEntity>()

        if (makeActive) {
            coursesService.getActiveSeason()?.run {
                savingSeasons.add(copy(active = false))
            }
        }

        val lastSeasonValue = coursesService.getLastSeason()?.value ?: 0
        savingSeasons.add(CourseSeasonEntity(
            id = 0,
            value = lastSeasonValue + 1,
            active = makeActive,
            year = getCurrentYear(),
            courses = emptyList()
        ))

        return coursesService.saveSeasons(savingSeasons).last()
    }

    fun removeSeason(seasonId: Long) {
        coursesService.removeSeason(getSeasonById(seasonId))
    }

    private fun getSeasonById(seasonId: Long): CourseSeasonEntity {
        return coursesService.getSeasonById(seasonId) ?: throw ValidationException("Season not found")
    }

    fun activateSeason(seasonId: Long) {
        coursesService.getActiveSeason()?.let {
            throw ValidationException("Only one season can have active status at same time")
        }
        val season = getSeasonById(seasonId).copy(active = true)
        coursesService.saveSeason(season)
    }

    fun deactivateSeason(seasonId: Long) {
        val season = getSeasonById(seasonId).copy(active = false)
        coursesService.saveSeason(season)
    }

    fun addCourse(seasonId: Long, name: String, type: CourseTypes): CourseEntity {
        val course = CourseEntity(
            id = 0,
            name = name,
            type = type,
            season = coursesService.getSeason(seasonId)
        )
        return coursesService.saveCourse(course)
    }

    private fun getCurrentYear(): Int = Year.now().value
}
