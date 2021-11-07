package ua.tarch64.maModuleApi.common.services

import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.security.Key
import javax.crypto.SecretKey

@Service
class EnvService {
    val authTokenSecret: String = System.getenv("APP_SECRET_KEY")
    val authTokenSecretKey: Key = createSecretKey()
    val authTokenExpiration: Int = System.getenv("APP_TOKEN_EXPIRATION").toInt()

    val frontendUrl: String = System.getenv("FRONTEND_URL")

    private fun createSecretKey(): SecretKey {
        val keyBytes = authTokenSecret.toByteArray(StandardCharsets.UTF_8)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}
