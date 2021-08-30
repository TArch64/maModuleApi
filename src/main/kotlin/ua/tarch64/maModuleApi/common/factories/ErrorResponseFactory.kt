package ua.tarch64.maModuleApi.common.factories

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.common.responses.ErrorResponse

@Service
class ErrorResponseFactory {
    fun createAuthResponse(message: String): ResponseEntity<ErrorResponse> {
        val body = ErrorResponse(message)
        return ResponseEntity.unprocessableEntity().body(body)
    }
}
