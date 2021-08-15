package ua.tarch64.maModuleApi.admin.courses

import org.springframework.stereotype.Component
import ua.tarch64.maModuleApi.admin.courses.services.CoursesAdminService
import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.courses.entities.CourseSeasonEntity
import ua.tarch64.maModuleApi.courses.enums.CourseTypes

@Component
class CoursesFacade(
    private val coursesService: CoursesAdminService
) {
    fun addActiveSeason(): CourseSeasonEntity {
        return coursesService.addActiveSeason()
    }

    fun getCourses(seasonId: Long): List<CourseEntity> {
        return coursesService.getSeasonCourses(seasonId)
    }

    fun addCourse(seasonId: Long, name: String, type: CourseTypes): CourseEntity {
        return coursesService.addCourse(seasonId, name, type)
    }
}
