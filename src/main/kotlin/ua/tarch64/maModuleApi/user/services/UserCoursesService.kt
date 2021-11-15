package ua.tarch64.maModuleApi.user.services

import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.courses.entities.CourseMentorEntity
import ua.tarch64.maModuleApi.courses.entities.CourseStudentEntity
import ua.tarch64.maModuleApi.courses.services.CoursesService
import ua.tarch64.maModuleApi.user.entities.UserEntity
import ua.tarch64.maModuleApi.user.entities.UserInvitationEntity

@Service
class UserCoursesService(val coursesService: CoursesService) {
    fun addUserToCourses(user: UserEntity, invitations: List<UserInvitationEntity>) {
        createMentors(user, invitations).also { coursesService.saveMentors(it) }
        createStudents(user, invitations).also { coursesService.saveStudents(it) }
    }

    private fun createMentors(user: UserEntity, invitations: List<UserInvitationEntity>): List<CourseMentorEntity> {
        return invitations.filter { it.role.isMentor }.map { CourseMentorEntity(course = it.course, user = user) }
    }

    private fun createStudents(user: UserEntity, invitations: List<UserInvitationEntity>): List<CourseStudentEntity> {
        return invitations.filter { it.role.isStudent }.map { CourseStudentEntity(course = it.course, user = user) }
    }
}
