package ua.tarch64.maModuleApi.courses.services

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.courses.entities.CourseMentorEntity
import ua.tarch64.maModuleApi.courses.enums.CourseMentorRoles
import ua.tarch64.maModuleApi.courses.repositories.CourseMentorsRepository
import ua.tarch64.maModuleApi.courses.repositories.CoursesRepository
import java.util.*

@Service
class CoursesService(
    private val coursesRepository: CoursesRepository,
    private val courseMentorsRepository: CourseMentorsRepository
) {
    fun getById(courseId: UUID): CourseEntity? {
        return coursesRepository.findByIdOrNull(courseId)
    }

    @Transactional
    fun save(course: CourseEntity): CourseEntity {
        return coursesRepository.save(course)
    }

    fun getCourseLeadMentor(course: CourseEntity): CourseMentorEntity? {
        return courseMentorsRepository.findByCourseAndRole(course, CourseMentorRoles.LEAD)
    }

    fun getMentorById(mentorId: UUID): CourseMentorEntity? {
        return courseMentorsRepository.findByIdOrNull(mentorId)
    }

    @Transactional
    fun saveMentor(courseMentor: CourseMentorEntity): CourseMentorEntity {
        return courseMentorsRepository.save(courseMentor)
    }

    @Transactional
    fun saveMentors(courseMentors: List<CourseMentorEntity>): List<CourseMentorEntity> {
        return courseMentorsRepository.saveAll(courseMentors)
    }

    @Transactional
    fun deleteCourse(course: CourseEntity) {
        coursesRepository.delete(course)
    }
}
