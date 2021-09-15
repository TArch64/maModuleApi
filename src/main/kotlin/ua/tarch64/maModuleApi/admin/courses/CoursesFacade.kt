package ua.tarch64.maModuleApi.admin.courses

import org.springframework.stereotype.Component
import ua.tarch64.maModuleApi.admin.courses.services.CourseSeasonsAdminService
import ua.tarch64.maModuleApi.admin.courses.services.CoursesAdminService
import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.courses.entities.CourseSeasonEntity
import ua.tarch64.maModuleApi.courses.enums.CourseTypes

@Component
class CoursesFacade(
    private val seasonsService: CourseSeasonsAdminService,
    private val coursesService: CoursesAdminService
) {
    fun getSeasons(): List<CourseSeasonEntity> {
        return seasonsService.getSeasons()
    }

    fun addSeason(makeActive: Boolean): CourseSeasonEntity {
        return seasonsService.addSeason(makeActive)
    }

    fun removeSeason(seasonId: Long) {
        seasonsService.removeSeason(seasonId)
    }

    fun activateSeason(seasonId: Long) {
        seasonsService.activateSeason(seasonId)
    }

    fun deactivateSeason(seasonId: Long) {
        seasonsService.deactivateSeason(seasonId)
    }

    fun getCourses(seasonId: Long): List<CourseEntity> {
        return seasonsService.getSeasonById(seasonId).courses
    }

    fun addCourse(seasonId: Long, name: String, type: CourseTypes): CourseEntity {
        return coursesService.addCourse(seasonId, name, type)
    }

    fun getCourseById(courseId: Long): CourseEntity? {
        return coursesService.getCourseById(courseId)
    }

    fun addMentor(courseId: Long, userId: Long) {
        coursesService.addMentor(courseId, userId)
    }
}
