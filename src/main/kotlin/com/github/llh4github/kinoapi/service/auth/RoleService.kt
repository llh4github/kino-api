package com.github.llh4github.kinoapi.service.auth

import com.github.llh4github.kinoapi.dto.IdsDto
import com.github.llh4github.kinoapi.dto.PageDto
import com.github.llh4github.kinoapi.dto.auth.RoleAddDto
import com.github.llh4github.kinoapi.dto.auth.RoleQueryDto
import com.github.llh4github.kinoapi.dto.auth.RoleUpdateDto
import com.github.llh4github.kinoapi.model.auth.Role
import org.springframework.data.domain.Page

/**
 *
 * Created At 2023/3/28 20:57
 * @author llh
 */
interface RoleService {
    fun allSimple(): List<Role>
    fun pageQuery(page: PageDto, query: RoleQueryDto?): Page<Role>

    /**
     * 根据ID查看详情
     */
    fun detail(id: Int): Role?

    fun deleteByIds(ids: IdsDto)

    fun existCode(code: String, notId: Int?): Boolean

    /**
     * 将输入的数据ID列表转换为数据库已有的ID列表(已去重)。
     *
     * 防止脏数据
     */
    fun idsInDb(idInput: List<Int>): List<Int>
    fun addByDto(dto: RoleAddDto): Boolean

    fun hasCode(code: String): Boolean

    /**
     * 根据[userId]查询对应的角色代码。
     *
     * 通常用于权限框架作角色判断。
     */
    fun roleCodes(userId: Int): List<String>

    /**
     * 根据[userId]查出对应角色的ID
     */
    fun ids(userId: Int): List<Int>
    fun updateByDto(dto: RoleUpdateDto): Boolean
}