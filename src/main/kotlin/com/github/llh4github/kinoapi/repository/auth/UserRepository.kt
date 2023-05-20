package com.github.llh4github.kinoapi.repository.auth

import com.jihulab.llh4gitlab.kinoapi.dto.auth.UserPwdUpdateDto
import com.jihulab.llh4gitlab.kinoapi.model.auth.*
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.valueIn

/**
 *
 * Created At 2023/3/29 18:48
 * @author llh
 */
interface UserRepository : KRepository<User, Int> {

    fun updateStatus(ids: List<Int>, status: Int): Int {
        if (ids.isEmpty()) return 0
        return sql.createUpdate(User::class) {
            set(table.status, status)
            where(table.id valueIn ids)
        }.execute()
    }

    fun updatePwd(dto: UserPwdUpdateDto): Int {
        return sql.createUpdate(User::class) {
            set(table.password, dto.hashedPwd)
            dto.updatedBy?.let {
                set(table.updatedBy, it)
            }
            dto.updatedTime?.let {
                set(table.updatedTime, it)
            }
            where(table.id eq dto.id)
        }.execute()
    }
}