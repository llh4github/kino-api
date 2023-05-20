package com.github.llh4github.kinoapi.repository.auth

import com.github.llh4github.kinoapi.model.auth.Permission
import org.babyfish.jimmer.spring.repository.KRepository

/**
 *
 * Created At 2023/3/31 15:07
 * @author llh
 */
interface PermissionRepository : KRepository<Permission, Int> {
}