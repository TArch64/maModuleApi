package ua.tarch64.maModuleApi.admin.courses.services

import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.admin.users.services.AdminUsersService
import ua.tarch64.maModuleApi.common.errorHandling.exceptions.ValidationException
import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.courses.entities.CourseMentorEntity
import ua.tarch64.maModuleApi.courses.entities.CourseStudentEntity
import ua.tarch64.maModuleApi.courses.enums.CourseMentorRoles
import ua.tarch64.maModuleApi.courses.enums.CourseTypes
import ua.tarch64.maModuleApi.courses.services.CoursesService
import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.enums.UserRoles
import ua.tarch64.maModuleApi.user.services.UserInvitationsService
import java.util.*

@Service
class CoursesAdminService(
    private val coursesService: CoursesService,
    private val seasonsService: SeasonsAdminService,
    private val usersService: AdminUsersService,
    private val userInvitationsService: UserInvitationsService
) {
    fun addCourse(seasonId: UUID, name: String, type: CourseTypes): CourseEntity {
        val course = CourseEntity(
            name = name,
            type = type,
            season = seasonsService.getSeasonById(seasonId)
        )
        return coursesService.save(course)
    }

    fun getCourseById(courseId: UUID): CourseEntity {
        return coursesService.getById(courseId) ?: throw ValidationException("Course not found")
    }

    fun addMentors(courseId: UUID, requestedEmails: List<String>): List<CourseMentorEntity> {
        val course = getCourseById(courseId)
        val emails = ignoreInvitedUserEmails(course, requestedEmails)
        val existingUsers = usersService.getByEmailsInRole(UserRoles.MENTOR, emails)

        if (emails.isEmpty()) return emptyList()

        if (emails.size > existingUsers.size) {
            val newUserEmails = fetchNewUserEmails(existingUsers, emails)
            userInvitationsService.invite(UserRoles.MENTOR, newUserEmails, course)
        }

        val mentors = existingUsers.map { CourseMentorEntity(course = course, user = it) }
        return coursesService.saveMentors(mentors)
    }

    private fun ignoreInvitedUserEmails(course: CourseEntity, emails: List<String>): List<String> {
        val existingMentorEmails = course.mentors.map { it.user.email }
        return emails.filterNot { existingMentorEmails.contains(it) }
    }

    private fun fetchNewUserEmails(existingUsers: List<UserEntity>, emails: List<String>): List<String> {
        val existingUserEmails = existingUsers.map { it.email }
        return emails.filterNot { existingUserEmails.contains(it) }
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

    fun deleteCourseById(courseId: UUID) {
        coursesService.deleteCourse(getCourseById(courseId))
    }

    fun removeMentor(mentorId: UUID) {
        coursesService.removeMentor(getMentorById(mentorId))
    }

    fun addStudents(courseId: UUID, requestedEmails: List<String>): List<CourseStudentEntity> {
        val course = getCourseById(courseId)
        val emails = ignoreInvitedUserEmails(course, requestedEmails)
        val existingUsers = usersService.getByEmailsInRole(UserRoles.STUDENT, emails)

        if (emails.isEmpty()) return emptyList()

        if (emails.size > existingUsers.size) {
            val newUserEmails = fetchNewUserEmails(existingUsers, emails)
            userInvitationsService.invite(UserRoles.STUDENT, newUserEmails, course)
        }

        val students = existingUsers.map { CourseStudentEntity(course = course, user = it) }
        return coursesService.saveStudents(students)
    }
}
