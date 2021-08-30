package ua.tarch64.maModuleApi.common.errorHandling.responses

data class ErrorResponse(
    val message: String,
    val details: Any? = null
)
