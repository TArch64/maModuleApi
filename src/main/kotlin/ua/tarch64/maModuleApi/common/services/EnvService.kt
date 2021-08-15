package ua.tarch64.maModuleApi.common.services

import org.springframework.stereotype.Service

@Service
class EnvService {
    val authTokenSecret: String = System.getenv("APP_SECRET_KEY")
    val authTokenExpiration: Int = System.getenv("APP_TOKEN_EXPIRATION").toInt()
}
