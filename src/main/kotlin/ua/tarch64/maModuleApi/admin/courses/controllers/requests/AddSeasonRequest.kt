package ua.tarch64.maModuleApi.admin.courses.controllers.requests

import javax.validation.constraints.NotNull

data class AddSeasonRequest(
    @NotNull(message = "'makeActive' can't be blank")
    val makeActive: Boolean
)
