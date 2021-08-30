package ua.tarch64.maModuleApi.common.errorHandling.responses

data class ErrorResponse(
    val message: String,
    val type: Types,
    val details: Any? = null
) {
    enum class Types {
        VALIDATION
    }
}
