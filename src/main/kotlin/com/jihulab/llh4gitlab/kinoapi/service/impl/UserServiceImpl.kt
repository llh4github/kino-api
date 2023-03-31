package com.jihulab.llh4gitlab.kinoapi.service.impl

import com.jihulab.llh4gitlab.kinoapi.dto.PageDto
import com.jihulab.llh4gitlab.kinoapi.dto.UserAddDto
import com.jihulab.llh4gitlab.kinoapi.dto.UserQueryDto
import com.jihulab.llh4gitlab.kinoapi.dto.convert.DtoConvert
import com.jihulab.llh4gitlab.kinoapi.model.User
import com.jihulab.llh4gitlab.kinoapi.model.id
import com.jihulab.llh4gitlab.kinoapi.model.username
import com.jihulab.llh4gitlab.kinoapi.repository.UserRepository
import com.jihulab.llh4gitlab.kinoapi.service.UserService
import com.jihulab.llh4gitlab.kinoapi.util.hashPwd
import org.apache.logging.log4j.kotlin.Logging
import org.babyfish.jimmer.sql.kt.ast.expression.count
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.like
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService, Logging {
    override fun findById(id: Int): User? {
        return userRepository.findNullable(id)
    }

    @Transactional
    override fun addByDto(dto: UserAddDto): Boolean {
        val input = DtoConvert.user.toUserInput(dto)
        input.password = hashPwd(dto.password)

        userRepository.insert(input)
        return true
    }

    override fun isExistUsername(username: String): Boolean {
        val rs = userRepository.sql.createQuery(User::class) {
            where(table.username eq username)
            select(count(table.id))
        }.fetchOneOrNull() ?: 0
        return rs > 0
    }

    override fun findByUsername(username: String): User? {
        return userRepository.sql.createQuery(User::class) {
            where(table.username eq username)
            select(table)
        }.fetchOneOrNull()
    }

    override fun pageQuery(page: PageDto, dto: UserQueryDto?): Page<User> {
        val condition = userRepository.sql.createQuery(User::class) {
            dto?.username?.takeIf { it.isNotEmpty() }?.let {
                where(table.username like it)
            }
            select(table)
        }
        return userRepository
            .pager(page.toPageRequest())
            .execute(condition)
    }
}