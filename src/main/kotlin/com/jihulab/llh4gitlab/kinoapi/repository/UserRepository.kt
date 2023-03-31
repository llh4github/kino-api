package com.jihulab.llh4gitlab.kinoapi.repository

import com.jihulab.llh4gitlab.kinoapi.model.User
import org.babyfish.jimmer.spring.repository.KRepository

/**
 *
 * Created At 2023/3/29 18:48
 * @author llh
 */
interface UserRepository : KRepository<User, Int> {
}