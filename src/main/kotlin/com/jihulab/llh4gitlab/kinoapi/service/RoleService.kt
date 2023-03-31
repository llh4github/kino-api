package com.jihulab.llh4gitlab.kinoapi.service

import com.jihulab.llh4gitlab.kinoapi.dto.PageDto
import com.jihulab.llh4gitlab.kinoapi.dto.RoleAddDto
import com.jihulab.llh4gitlab.kinoapi.dto.RoleQueryDto
import com.jihulab.llh4gitlab.kinoapi.model.Role
import org.springframework.data.domain.Page

/**
 *
 * Created At 2023/3/28 20:57
 * @author llh
 */
interface RoleService {
    fun all(): List<Role>
    fun pageQuery(page: PageDto, query: RoleQueryDto?): Page<Role>

    /**
     * 将输入的数据ID列表转换为数据库已有的ID列表(已去重)。
     *
     * 防止脏数据
     */
    fun idsInDb(idInput: List<Int>): List<Int>
    fun addByDto(dto: RoleAddDto): Boolean

    /**
     * 根据[userId]查询对应的角色代码。
     *
     * 通常用于权限框架作角色判断。
     */
    fun roleCodes(userId: Int): List<String>
}