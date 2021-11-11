package ua.tarch64.maModuleApi.admin.courses

import org.springframework.stereotype.Component
import ua.tarch64.maModuleApi.admin.courses.services.SeasonsAdminService
import ua.tarch64.maModuleApi.admin.courses.services.CoursesAdminService
import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.courses.entities.CourseMentorEntity
import ua.tarch64.maModuleApi.courses.entities.SeasonEntity
import ua.tarch64.maModuleApi.courses.enums.CourseTypes
import java.util.*

@Component
class CoursesFacade(
    private val seasonsService: SeasonsAdminService,
    private val coursesService: CoursesAdminService
) {
    fun getSeasons(): List<SeasonEntity> {
        return seasonsService.getSeasons()
    }

    fun addSeason(makeActive: Boolean): SeasonEntity {
        return seasonsService.addSeason(makeActive)
    }

    fun removeSeason(seasonId: UUID) {
        seasonsService.removeSeason(seasonId)
    }

    fun activateSeason(seasonId: UUID) {
        seasonsService.activateSeason(seasonId)
    }

    fun deactivateSeason(seasonId: UUID) {
        seasonsService.deactivateSeason(seasonId)
    }

    fun getCourses(seasonId: UUID): List<CourseEntity> {
        return seasonsService.getSeasonById(seasonId).courses
    }

    fun addCourse(seasonId: UUID, name: String, type: CourseTypes): CourseEntity {
        return coursesService.addCourse(seasonId, name, type)
    }

    fun getCourseById(courseId: UUID): CourseEntity? {
        return coursesService.getCourseById(courseId)
    }

    fun addMentors(courseId: UUID, emails: List<String>): List<CourseMentorEntity> {
        return coursesService.addMentors(courseId, emails)
    }

    fun changeLeadMentor(courseId: UUID, mentorId: UUID) {
        return coursesService.changeLeadMentor(courseId, mentorId)
    }

    fun deleteCourse(courseId: UUID) {
        coursesService.deleteCourseById(courseId)
    }
}
