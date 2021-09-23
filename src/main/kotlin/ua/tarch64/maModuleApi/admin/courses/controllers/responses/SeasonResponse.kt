package ua.tarch64.maModuleApi.admin.courses.controllers.responses

import ua.tarch64.maModuleApi.courses.entities.SeasonEntity
import java.util.*

data class SeasonResponse(
    val id: UUID,
    val value: Int,
    val year: Int,
    val active: Boolean
) {
    companion object {
        fun fromEntity(entity: SeasonEntity): SeasonResponse {
            return SeasonResponse(entity.id, entity.value, entity.year, entity.active)
        }
    }
}
