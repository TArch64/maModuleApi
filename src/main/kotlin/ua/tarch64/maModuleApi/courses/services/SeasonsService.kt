package ua.tarch64.maModuleApi.courses.services

import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ua.tarch64.maModuleApi.common.helpers.asKOptional
import ua.tarch64.maModuleApi.courses.entities.SeasonEntity
import ua.tarch64.maModuleApi.courses.repositories.SeasonsRepository
import java.util.*

@Service
class SeasonsService(private val seasonsRepository: SeasonsRepository) {
    fun getLast(): SeasonEntity? {
        return seasonsRepository.findLastSeason()
    }

    fun getAll(): List<SeasonEntity> {
        return seasonsRepository.findAll(Sort.by(Sort.Direction.DESC, "value"))
    }

    fun getActive(): SeasonEntity? {
        return seasonsRepository.findByActiveTrue().asKOptional()
    }

    fun getById(id: UUID): SeasonEntity? {
        return seasonsRepository.findByIdOrNull(id)
    }

    fun remove(season: SeasonEntity) {
        seasonsRepository.delete(season)
    }

    @Transactional
    fun save(season: SeasonEntity): SeasonEntity {
        return seasonsRepository.save(season)
    }

    @Transactional
    fun save(seasons: List<SeasonEntity>): List<SeasonEntity> {
        return seasonsRepository.saveAllAndFlush(seasons)
    }
}
