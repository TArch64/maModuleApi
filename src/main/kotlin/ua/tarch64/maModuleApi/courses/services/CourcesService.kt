package ua.tarch64.maModuleApi.courses.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ua.tarch64.maModuleApi.common.helpers.asKOptional
import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.courses.entities.CourseMentorEntity
import ua.tarch64.maModuleApi.courses.repositories.CourseMentorsRepository
import ua.tarch64.maModuleApi.courses.repositories.CoursesRepository

@Service
class CoursesService(
    private val coursesRepository: CoursesRepository,
    private val courseMentorsRepository: CourseMentorsRepository
) {
    fun getById(courseId: Long): CourseEntity? {
        return coursesRepository.findById(courseId).asKOptional()
    }

    @Transactional
    fun save(course: CourseEntity): CourseEntity {
        return coursesRepository.save(course)
    }

    @Transactional
    fun saveMentor(courseMentor: CourseMentorEntity): CourseMentorEntity {
        return courseMentorsRepository.save(courseMentor)
    }
}
