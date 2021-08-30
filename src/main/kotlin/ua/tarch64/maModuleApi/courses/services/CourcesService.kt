package ua.tarch64.maModuleApi.courses.services

import org.springframework.data.domain.Sort
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

    fun getLastSeason(): CourseSeasonEntity? {
        return courseSeasonsRepository.findLastSeason()
    }

    fun getSeasons(): List<CourseSeasonEntity> {
        return courseSeasonsRepository.findAll(Sort.by(Sort.Direction.DESC, "value"))
    }

    @Transactional
    fun saveSeason(season: CourseSeasonEntity): CourseSeasonEntity {
        return courseSeasonsRepository.save(season)
    }

    @Transactional
    fun saveCourse(course: CourseEntity): CourseEntity {
        return coursesRepository.save(course)
    }
}
