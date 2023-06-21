package com.github.llh4github.kinoapi.service.auth.impl

import cn.dev33.satoken.stp.StpUtil
import com.github.llh4github.kinoapi.contanst.ErrorCode
import com.github.llh4github.kinoapi.dto.PageDto
import com.github.llh4github.kinoapi.dto.auth.*
import com.github.llh4github.kinoapi.dto.convert.DtoConvert
import com.github.llh4github.kinoapi.exception.AppException
import com.github.llh4github.kinoapi.model.auth.*
import com.github.llh4github.kinoapi.model.auth.helper.UserInput
import com.github.llh4github.kinoapi.repository.auth.RoleRepository
import com.github.llh4github.kinoapi.repository.auth.UserRepository
import com.github.llh4github.kinoapi.service.auth.UserService
import com.github.llh4github.kinoapi.util.checkPwd
import com.github.llh4github.kinoapi.util.hashPwd
import org.apache.logging.log4j.kotlin.Logging
import org.babyfish.jimmer.sql.kt.ast.expression.count
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.like
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.springframework.beans.BeanUtils
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
) : UserService, Logging {
    override fun findById(id: Int): User? {
        return userRepository.findNullable(id)
    }

    override fun fetchInfo(id: Int): UserInfoDto? {
        return userRepository.findNullable(id)?.run {
            val roles = roleRepository.findRoleCodeByUserId(id)
            UserInfoDto(
                id = this.id, username = this.username,
                avatar = "https://assets.qszone.com/images/avatar.jpg", // 先用别人的
                email = "test@email.com",
                roles,
            )
        }
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

    override fun detailWithRole(id: Int): User? {
        return userRepository.findNullable(id, newFetcher(User::class).by {
            allScalarFields()
            roles {
                name()
                code()
            }
        })
    }

    @Transactional
    override fun updateUserStatus(dto: UserStatusUpdateDto): Int {
        return userRepository.updateStatus(dto.ids, dto.status)
    }

    @Transactional
    override fun updatePwd(dto: UserPwdUpdateDto): Int {
        return userRepository.updatePwd(dto)
    }

    @Transactional
    override fun addByDto(dto: UserAddDto): Boolean {
        val builder = dto.toJimmerEntityBuilder()
        val hashed = hashPwd(dto.password)
        builder.password(hashed)
        userRepository.insert(builder.build())
        return true
    }

    @Transactional
    override fun updateByDto(dto: UserUpdateDto): Boolean {
        val input = DtoConvert.user.toDbInput(dto)
        userRepository.update(input)
        return true
    }

    override fun isExistUsername(username: String, notId: Int?): Boolean {
        val rs = userRepository.sql.createQuery(User::class) {
            where(table.username eq username)
            notId?.let {
                where(table.id eq notId)
            }
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
        val fetcher = newFetcher(User::class).by {
            allScalarFields()
            roles {
                name()
            }
        }
        val condition = userRepository.sql.createQuery(User::class) {
            dto?.username?.takeIf { it.isNotEmpty() }?.let {
                where(table.username like it)
            }
            orderBy(table.updatedTime.desc())
            select(table.fetch(fetcher))
        }

        return userRepository
            .pager(page.toPageRequest())
            .execute(condition)
    }
}