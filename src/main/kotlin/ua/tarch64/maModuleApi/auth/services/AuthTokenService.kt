package ua.tarch64.maModuleApi.auth.services

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import ua.tarch64.maModuleApi.common.services.EnvService
import java.util.*

@Service
class AuthTokenService(private val envService: EnvService) {
    fun generateToken(user: User): String {
        return Jwts.builder()
            .setSubject(user.username)
            .setExpiration(Date(System.currentTimeMillis() + envService.authTokenExpiration))
            .claim("roles", user.authorities.map { it.authority })
            .signWith(SignatureAlgorithm.HS256, envService.authTokenSecret)
            .compact()
    }
}
