package io.github.llh4github.kinoapi.service.auth

import io.github.llh4github.kinoapi.model.auth.User
import io.github.llh4github.kinoapi.model.auth.dto.UserSimpleView
import org.babyfish.jimmer.sql.fetcher.Fetcher

/**
 *
 * Created At 2023/12/3 15:27
 * @author llh
 */
interface UserService {
    fun findById(
        id: Int,
        fetcher: Fetcher<User> = UserSimpleView.METADATA.fetcher
    ): User?
}