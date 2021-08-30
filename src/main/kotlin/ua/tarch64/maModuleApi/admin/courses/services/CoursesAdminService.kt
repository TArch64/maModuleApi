package ua.tarch64.maModuleApi.admin.courses.services

import org.springframework.stereotype.Service
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
        val newSeason = CourseSeasonEntity(
            id = 0,
            value = (lastSeason?.value ?: 0) + 1,
            active = true,
            year = getCurrentYear(),
            courses = emptyList()
        )
        return coursesService.saveSeason(newSeason)
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
