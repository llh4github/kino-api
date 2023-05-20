package com.github.llh4github.kinoapi.service.auth

import com.jihulab.llh4gitlab.kinoapi.dto.PageDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.*
import com.jihulab.llh4gitlab.kinoapi.model.auth.User
import org.springframework.data.domain.Page

/**
 *
 * Created At 2023/3/29 18:36
 * @author llh
 */
interface UserService {
    fun findById(id: Int): User?

    fun fetchInfo(id: Int): UserInfoDto?
    fun addByDto(dto: UserAddDto): Boolean
    fun updateByDto(dto: UserUpdateDto): Boolean

    fun updateUserStatus(dto: UserStatusUpdateDto): Int

    fun updatePwd(dto: UserPwdUpdateDto): Int

    /**
     * 带有角色信息的详情
     */
    fun detailWithRole(id: Int): User?
    fun login(dto: LoginDto): LoginTokenDto

    /**
     * 用户名[username]是否已经存在。返回true则存在
     */
    fun isExistUsername(username: String, notId: Int?): Boolean

    /**
     * 根据用户名[username]查询
     */
    fun findByUsername(username: String): User?

    fun pageQuery(page: PageDto, dto: UserQueryDto? = null): Page<User>
}