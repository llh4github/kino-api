package com.jihulab.llh4gitlab.kinoapi.service.auth.impl

import cn.dev33.satoken.stp.StpUtil
import com.jihulab.llh4gitlab.kinoapi.contanst.ErrorCode
import com.jihulab.llh4gitlab.kinoapi.dto.PageDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.LoginDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.LoginTokenDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.UserAddDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.UserQueryDto
import com.jihulab.llh4gitlab.kinoapi.dto.convert.DtoConvert
import com.jihulab.llh4gitlab.kinoapi.exception.AppException
import com.jihulab.llh4gitlab.kinoapi.model.auth.User
import com.jihulab.llh4gitlab.kinoapi.model.auth.id
import com.jihulab.llh4gitlab.kinoapi.model.auth.username
import com.jihulab.llh4gitlab.kinoapi.repository.auth.UserRepository
import com.jihulab.llh4gitlab.kinoapi.service.auth.UserService
import com.jihulab.llh4gitlab.kinoapi.util.checkPwd
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

    override fun login(dto: LoginDto): LoginTokenDto {
        val u = findByUsername(dto.username) ?: throw AppException(ErrorCode.USER_PWD_ERROR)
        if (!checkPwd(dto.password, u.password)) {
            logger.info("用户 ${dto.username} 输入密码错误")
            throw AppException(ErrorCode.USER_PWD_ERROR)
        }
        StpUtil.login(u.id)
        val info = StpUtil.getTokenInfo()
        return LoginTokenDto(
            username = u.username,
            accessToken = info.tokenValue
        )
    }

    @Transactional
    override fun addByDto(dto: UserAddDto): Boolean {
        val input = DtoConvert.user.toDbInput(dto)
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