package com.jihulab.llh4gitlab.kinoapi.repository.auth

import com.jihulab.llh4gitlab.kinoapi.model.auth.Role
import com.jihulab.llh4gitlab.kinoapi.model.auth.code
import com.jihulab.llh4gitlab.kinoapi.model.auth.id
import com.jihulab.llh4gitlab.kinoapi.model.auth.users
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.eq

/**
 *
 * Created At 2023/3/28 20:57
 * @author llh
 */
interface RoleRepository : KRepository<Role, Int> {
    fun existsByCode(code: String): Boolean

    fun findRoleCodeByUserId(userId:Int): List<String> {
        return sql.createQuery(Role::class) {
            where(table.asTableEx().users.id eq userId)
            select(table.code)
        }.execute()
    }
}