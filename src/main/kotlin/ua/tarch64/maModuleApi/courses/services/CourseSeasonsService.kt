package ua.tarch64.maModuleApi.courses.services

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ua.tarch64.maModuleApi.common.helpers.asKOptional
import ua.tarch64.maModuleApi.courses.entities.CourseSeasonEntity
import ua.tarch64.maModuleApi.courses.repositories.CourseSeasonsRepository

@Service
class CourseSeasonsService(private val courseSeasonsRepository: CourseSeasonsRepository) {
    fun getLast(): CourseSeasonEntity? {
        return courseSeasonsRepository.findLastSeason()
    }

    fun getAll(): List<CourseSeasonEntity> {
        return courseSeasonsRepository.findAll(Sort.by(Sort.Direction.DESC, "value"))
    }

    fun getActive(): CourseSeasonEntity? {
        return courseSeasonsRepository.findByActiveTrue().asKOptional()
    }

    fun getById(id: Long): CourseSeasonEntity? {
        return courseSeasonsRepository.findById(id).asKOptional()
    }

    fun remove(season: CourseSeasonEntity) {
        courseSeasonsRepository.delete(season)
    }

    @Transactional
    fun save(season: CourseSeasonEntity): CourseSeasonEntity {
        return courseSeasonsRepository.save(season)
    }

    @Transactional
    fun save(seasons: List<CourseSeasonEntity>): List<CourseSeasonEntity> {
        return courseSeasonsRepository.saveAllAndFlush(seasons)
    }
}
