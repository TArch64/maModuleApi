package ua.tarch64.maModuleApi.courses.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ua.tarch64.maModuleApi.courses.entities.CourseEntity

@Repository
interface CoursesRepository: JpaRepository<CourseEntity, Long> {
    @Query("""
        SELECT * from course as c
        WHERE c.season_id = :season_id AND LOWER(c.name) = LOWER(:name)
    """, nativeQuery = true)
    fun findByName(@Param("season_id") seasonId: Long, @Param("name") name: String): CourseEntity?
}
