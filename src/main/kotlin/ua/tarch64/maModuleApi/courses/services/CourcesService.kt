package ua.tarch64.maModuleApi.courses.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ua.tarch64.maModuleApi.courses.entities.CourseEntity
import ua.tarch64.maModuleApi.courses.entities.CourseSeasonEntity
import ua.tarch64.maModuleApi.courses.repositories.CourseSeasonsRepository
import ua.tarch64.maModuleApi.courses.repositories.CoursesRepository

@Service
class CoursesService(
    private val courseSeasonsRepository: CourseSeasonsRepository,
    private val coursesRepository: CoursesRepository
) {
    fun getSeason(seasonId: Long): CourseSeasonEntity {
        return courseSeasonsRepository.findById(seasonId).get()
    }

    fun getSeasonCourses(seasonId: Long): List<CourseEntity> {
        return getSeason(seasonId).courses
    }

    fun getActiveSeason(): CourseSeasonEntity? {
        return courseSeasonsRepository.findByActiveTrue()
    }

    @Transactional
    fun saveSeasons(seasons: List<CourseSeasonEntity>): List<CourseSeasonEntity> {
        return courseSeasonsRepository.saveAll(seasons)
    }

    @Transactional
    fun saveCourse(course: CourseEntity): CourseEntity {
        return coursesRepository.save(course)
    }
}
