package ua.tarch64.maModuleApi.courses.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ua.tarch64.maModuleApi.courses.entities.SeasonEntity
import java.util.*

@Repository
interface SeasonsRepository: JpaRepository<SeasonEntity, UUID> {
    @Query("""
        SELECT * FROM seasons AS cs
        WHERE cs.value = (SELECT MAX(value) FROM seasons)
        LIMIT 1
    """, nativeQuery = true)
    fun findLastSeason(): SeasonEntity?

    fun findByActiveTrue(): Optional<SeasonEntity>
}
