package ua.tarch64.maModuleApi.common.errorHandling.exceptions

class ValidationException(
    override val message: String,
    val details: Any? = null
): Exception()
