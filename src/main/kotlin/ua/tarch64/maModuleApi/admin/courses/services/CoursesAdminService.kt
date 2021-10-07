package ua.tarch64.maModuleApi.admin.courses.services

import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.admin.users.services.AdminUsersService
import ua.tarch64.maModuleApi.common.errorHandling.exceptions.ValidationException
import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.courses.entities.CourseMentorEntity
import ua.tarch64.maModuleApi.courses.enums.CourseMentorRoles
import ua.tarch64.maModuleApi.courses.enums.CourseTypes
import ua.tarch64.maModuleApi.courses.services.CoursesService
import ua.tarch64.maModuleApi.user.enums.UserRoles
import java.util.*

@Service
class CoursesAdminService(
    private val coursesService: CoursesService,
    private val seasonsService: SeasonsAdminService,
    private val usersService: AdminUsersService
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

    fun addMentors(courseId: UUID, emails: List<String>): List<CourseMentorEntity> {
        val course = getCourseById(courseId)
        val users = usersService.getByEmailsInRole(UserRoles.MENTOR, emails)
        val mentors = users.map { CourseMentorEntity(course = course, user = it) }
        return coursesService.saveMentors(mentors)
    }

    fun changeLeadMentor(courseId: UUID, mentorId: UUID) {
        val course = getCourseById(courseId)
        val mentor = getMentorById(mentorId)
        val savingMentors = mutableListOf<CourseMentorEntity>()

        coursesService.getCourseLeadMentor(course)?.run {
            savingMentors.add(copy(role = CourseMentorRoles.MENTOR))
        }

        savingMentors.add(mentor.copy(role = CourseMentorRoles.LEAD))
        coursesService.saveMentors(savingMentors)
    }

    fun getMentorById(mentorId: UUID): CourseMentorEntity {
        return coursesService.getMentorById(mentorId) ?: throw ValidationException("Mentor not found")
    }
}
