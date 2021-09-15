package ua.tarch64.maModuleApi.admin.courses.services

import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.common.errorHandling.exceptions.ValidationException
import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.courses.entities.CourseMentorEntity
import ua.tarch64.maModuleApi.courses.enums.CourseTypes
import ua.tarch64.maModuleApi.courses.services.CoursesService

@Service
class CoursesAdminService(
    private val coursesService: CoursesService,
    private val seasonsService: CourseSeasonsAdminService,
    private val usersService: UsersAdminService
) {
    fun addCourse(seasonId: Long, name: String, type: CourseTypes): CourseEntity {
        val course = CourseEntity(
            name = name,
            type = type,
            season = seasonsService.getSeasonById(seasonId)
        )
        return coursesService.save(course)
    }

    fun addMentor(courseId: Long, userId: Long) {
        val courseMentor = CourseMentorEntity(
            course = getCourseById(courseId),
            user = usersService.getUserById(userId)
        )
        coursesService.saveMentor(courseMentor)
    }

    fun getCourseById(courseId: Long): CourseEntity {
        return coursesService.getById(courseId) ?: throw ValidationException("Course not found")
    }
}
