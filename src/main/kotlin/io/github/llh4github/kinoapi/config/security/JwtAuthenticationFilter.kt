package io.github.llh4github.kinoapi.config.security

import io.github.llh4github.kinoapi.constants.AUTHENTICATION_HEAD
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

/**
 *
 * Created At 2023/12/16 11:50
 * @author llh
 */
@Component
class JwtAuthenticationFilter(
    val jwtProcess: JwtProcess,
    val userDetailsService: UserDetailsService,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = getJwt(request)
        if (jwt != null && jwtProcess.validateJwt(jwt)) {
            jwtProcess.parseUsername(jwt)?.let {
                try {
                    val detail = userDetailsService.loadUserByUsername(it)
                    val token = UsernamePasswordAuthenticationToken(detail.username, null, detail.authorities)
                    SecurityContextHolder.getContext().authentication = token
                } catch (e: Exception) {
                    logger.error("jwt 中的用户名查不到对应的用户 username: $it")
                }

            }
        }
        filterChain.doFilter(request, response)
    }

    private fun getJwt(request: HttpServletRequest): String? {
        return request.getHeader(AUTHENTICATION_HEAD)
    }
}