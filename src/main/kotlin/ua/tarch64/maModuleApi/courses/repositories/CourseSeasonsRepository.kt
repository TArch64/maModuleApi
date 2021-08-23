package ua.tarch64.maModuleApi.courses.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ua.tarch64.maModuleApi.courses.entities.CourseSeasonEntity

@Repository
interface CourseSeasonsRepository: JpaRepository<CourseSeasonEntity, Long> {
    @Query("""
        SELECT * FROM course_seasons as cs
        WHERE cs.active = true OR cs.value = (SELECT MAX(value) FROM course_seasons)
        LIMIT 1
    """, nativeQuery = true)
    fun findLastSeason(): CourseSeasonEntity?
}
