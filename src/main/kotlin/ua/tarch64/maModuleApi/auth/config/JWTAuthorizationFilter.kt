package ua.tarch64.maModuleApi.auth.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import ua.tarch64.maModuleApi.common.services.EnvService
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthorizationFilter(
    authManager: AuthenticationManager,
    private val envService: EnvService
) : BasicAuthenticationFilter(authManager) {
    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        val token = getToken(req)
        if (token == null) {
            chain.doFilter(req, res)
            return
        }
        SecurityContextHolder.getContext().authentication = getAuthentication(token)
        chain.doFilter(req, res)
    }

    private fun getAuthentication(token: String): Authentication? {
        val username: String
        val roles: List<GrantedAuthority>
        try {
            val claims: Claims = Jwts.parserBuilder()
                .setSigningKey(envService.authTokenSecretKey)
                .build()
                .parseClaimsJws(token)
                .body
            username = claims.subject
            roles = claims.get("roles", List::class.java).map { SimpleGrantedAuthority(it as String) }
        } catch (e: JwtException) {
            return null
        }
        if (username == null) return null
        return createAuthToken(username, roles)
    }

    private fun getToken(req: HttpServletRequest): String? {
        val token = req.getHeader("Authorization")
        if (!token.orEmpty().startsWith("Bearer ")) return null
        return token.replace("Bearer ", "")
    }

    private fun createAuthToken(username: String, roles: List<GrantedAuthority>): Authentication {
        val user = User(username, "secure", roles)
        return UsernamePasswordAuthenticationToken(user, null, roles)
    }
}
