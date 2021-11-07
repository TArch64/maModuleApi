package ua.tarch64.maModuleApi.common.services

import org.springframework.stereotype.Service
import java.util.*

@Service
class UrlService(private val envService: EnvService) {
    fun buildJoinUrl(invitationId: UUID): String {
        return buildUrl(listOf(envService.frontendUrl, "auth/join"), mapOf("iid" to invitationId.toString()))
    }

    fun buildUrl(paths: List<String>, params: Map<String, String>): String {
        val query = params.map { "${it.key}=${it.value}" }.joinToString("&")
        val url = paths.joinToString("/")
        return "$url?$query"
    }
}
