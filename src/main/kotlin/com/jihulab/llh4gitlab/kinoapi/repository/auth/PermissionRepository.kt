package com.jihulab.llh4gitlab.kinoapi.repository.auth

import com.jihulab.llh4gitlab.kinoapi.model.auth.Permission
import org.babyfish.jimmer.spring.repository.KRepository

/**
 *
 * Created At 2023/3/31 15:07
 * @author llh
 */
interface PermissionRepository : KRepository<Permission, Int> {
}