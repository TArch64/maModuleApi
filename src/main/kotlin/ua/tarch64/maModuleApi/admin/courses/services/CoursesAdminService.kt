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

    fun addActiveSeason(): CourseSeasonEntity {
        val savingSeasons = mutableListOf<CourseSeasonEntity>()

        val lastSeason = coursesService.getActiveSeason()?.apply {
            active = false
            savingSeasons.add(this)
        }

        savingSeasons.add(CourseSeasonEntity(
            id = 0,
            value = (lastSeason?.value ?: 0) + 1,
            active = true,
            year = Year.now().value,
            courses = emptyList()
        ))

        return coursesService.saveSeasons(savingSeasons).last()
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
}
