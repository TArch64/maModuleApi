package ua.tarch64.maModuleApi.admin.courses.services

import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.common.errorHandling.exceptions.ValidationException
import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.courses.entities.CourseMentorEntity
import ua.tarch64.maModuleApi.courses.enums.CourseTypes
import ua.tarch64.maModuleApi.courses.services.CoursesService
import java.util.*

@Service
class CoursesAdminService(
    private val coursesService: CoursesService,
    private val seasonsService: SeasonsAdminService,
    private val usersService: UsersAdminService
) {
    fun addCourse(seasonId: UUID, name: String, type: CourseTypes): CourseEntity {
        val course = CourseEntity(
            name = name,
            type = type,
            season = seasonsService.getSeasonById(seasonId)
        )
        return coursesService.save(course)
    }

    fun addMentor(courseId: UUID, userId: UUID) {
        val courseMentor = CourseMentorEntity(
            course = getCourseById(courseId),
            user = usersService.getUserById(userId)
        )
        coursesService.saveMentor(courseMentor)
    }

    fun getCourseById(courseId: UUID): CourseEntity {
        return coursesService.getById(courseId) ?: throw ValidationException("Course not found")
    }
}
