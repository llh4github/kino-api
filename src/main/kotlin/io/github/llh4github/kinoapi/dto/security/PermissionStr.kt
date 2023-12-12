package io.github.llh4github.kinoapi.dto.security

import org.springframework.security.core.GrantedAuthority

/**
 *
 * Created At 2023/12/11 22:32
 * @author llh
 */
data class PermissionStr(private val str: String) : GrantedAuthority {
    override fun getAuthority(): String {
        return str
    }
}
