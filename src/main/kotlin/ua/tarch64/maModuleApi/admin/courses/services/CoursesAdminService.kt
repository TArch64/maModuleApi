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
        val lastSeasonValue = coursesService.getLastSeason()?.value ?: 0
        return coursesService.saveSeason(CourseSeasonEntity(
            id = 0,
            value = lastSeasonValue + 1,
            active = true,
            year = getCurrentYear(),
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
