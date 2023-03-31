package com.jihulab.llh4gitlab.kinoapi.service

import com.jihulab.llh4gitlab.kinoapi.dto.PageDto
import com.jihulab.llh4gitlab.kinoapi.dto.UserAddDto
import com.jihulab.llh4gitlab.kinoapi.dto.UserQueryDto
import com.jihulab.llh4gitlab.kinoapi.model.User
import org.springframework.data.domain.Page

/**
 *
 * Created At 2023/3/29 18:36
 * @author llh
 */
interface UserService {
    fun findById(id: Int): User?

    fun addByDto(dto: UserAddDto): Boolean

    /**
     * 用户名[username]是否已经存在。返回true则存在
     */
    fun isExistUsername(username: String): Boolean

    /**
     * 根据用户名[username]查询
     */
    fun findByUsername(username: String): User?

    fun pageQuery(page: PageDto, dto: UserQueryDto? = null): Page<User>
}