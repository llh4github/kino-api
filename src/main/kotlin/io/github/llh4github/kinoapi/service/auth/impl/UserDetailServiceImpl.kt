package io.github.llh4github.kinoapi.service.auth.impl

import io.github.llh4github.kinoapi.dto.security.UserAuthInfo
import io.github.llh4github.kinoapi.exceptions.AppException
import io.github.llh4github.kinoapi.exceptions.RespMsgEnums
import io.github.llh4github.kinoapi.model.auth.User
import io.github.llh4github.kinoapi.model.auth.dto.UserAuthView
import io.github.llh4github.kinoapi.model.auth.username
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

/**
 *
 * Created At 2023/12/11 22:44
 * @author llh
 */
@Service
class UserDetailServiceImpl(
    private val sqlClient: KSqlClient
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = sqlClient.createQuery(User::class) {
            where(table.username eq username)
            select(table.fetch(UserAuthView.METADATA.fetcher))
        }.fetchOneOrNull() ?: throw AppException(RespMsgEnums.DATA_NOT_FOUND)
        val roles = user.roles.map { it.code }.toList()
        val permissions = user.roles.flatMap { it.permissions }.map { it.code }.toList()
        return UserAuthInfo(user, roles + permissions)
    }
}