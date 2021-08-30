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

    fun addSeason(): CourseSeasonEntity {
        val lastSeason = coursesService.getLastSeason()
        val currentYear = getCurrentYear()

        if (lastSeason?.year == currentYear) {
            throw ValidationException("Season for this year already exists")
        }

        return coursesService.saveSeason(CourseSeasonEntity(
            id = 0,
            value = (lastSeason?.value ?: 0) + 1,
            active = true,
            year = currentYear,
            courses = emptyList()
        ))
    }

    fun finishActiveSeason() {
        val activeSeason = coursesService.getActiveSeason() ?: throw ValidationException("There are no active seasons")
        coursesService.saveSeason(activeSeason.copy(active = false))
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
