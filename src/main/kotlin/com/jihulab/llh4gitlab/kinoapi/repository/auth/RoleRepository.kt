package com.jihulab.llh4gitlab.kinoapi.repository.auth

import com.jihulab.llh4gitlab.kinoapi.model.auth.Role
import org.babyfish.jimmer.spring.repository.KRepository

/**
 *
 * Created At 2023/3/28 20:57
 * @author llh
 */
interface RoleRepository : KRepository<Role, Int> {
    fun existsByCode(code: String): Boolean
}