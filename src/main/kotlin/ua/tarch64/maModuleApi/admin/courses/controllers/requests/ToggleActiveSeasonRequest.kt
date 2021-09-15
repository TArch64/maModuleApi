package ua.tarch64.maModuleApi.admin.courses.controllers.requests

import javax.validation.constraints.NotNull

data class ToggleActiveSeasonRequest(
    @NotNull(message = "'seasonId' can't be blank")
    val seasonId: Long
)

