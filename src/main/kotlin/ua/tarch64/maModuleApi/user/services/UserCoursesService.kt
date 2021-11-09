package ua.tarch64.maModuleApi.user.services

import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.courses.entities.CourseMentorEntity
import ua.tarch64.maModuleApi.courses.services.CoursesService
import ua.tarch64.maModuleApi.user.entities.UserEntity

@Service
class UserCoursesService(val coursesService: CoursesService) {
    fun addUserToCourses(user: UserEntity, courses: List<CourseEntity>) {
        val mentors = courses.map { CourseMentorEntity(course = it, user = user) }
        coursesService.saveMentors(mentors)
    }
}
