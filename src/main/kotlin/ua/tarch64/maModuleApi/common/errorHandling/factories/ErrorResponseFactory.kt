package ua.tarch64.maModuleApi.common.errorHandling.factories

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.common.errorHandling.exceptions.ValidationException
import ua.tarch64.maModuleApi.common.errorHandling.responses.ErrorResponse

@Service
class ErrorResponseFactory {
    fun createValidationResponse(exception: ValidationException): ResponseEntity<ErrorResponse> {
        val body = ErrorResponse(exception.message, ErrorResponse.Types.VALIDATION, exception.details)
        return ResponseEntity.unprocessableEntity().body(body)
    }

    fun createValidationResponse(message: String): ResponseEntity<ErrorResponse> {
        return createValidationResponse(ValidationException(message))
    }
}
