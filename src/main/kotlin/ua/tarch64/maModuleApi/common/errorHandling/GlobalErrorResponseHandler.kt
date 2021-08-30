package ua.tarch64.maModuleApi.common.errorHandling

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ua.tarch64.maModuleApi.common.errorHandling.exceptions.ValidationException
import ua.tarch64.maModuleApi.common.errorHandling.factories.ErrorResponseFactory
import ua.tarch64.maModuleApi.common.errorHandling.responses.ErrorResponse

@ControllerAdvice
class GlobalErrorResponseHandler(private val errorResponseFactory: ErrorResponseFactory) {
    @ExceptionHandler(ValidationException::class)
    fun handleValidationException(exception: ValidationException): ResponseEntity<ErrorResponse> {
        return errorResponseFactory.createValidationResponse(exception)
    }
}
