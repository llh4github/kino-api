package com.github.llh4github.kinoapi.service.auth

import com.jihulab.llh4gitlab.kinoapi.dto.IdDto
import com.jihulab.llh4gitlab.kinoapi.dto.PageDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.PermissionAddDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.PermissionQueryDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.PermissionUpdateDto
import com.jihulab.llh4gitlab.kinoapi.model.auth.Permission
import org.springframework.data.domain.Page

/**
 *
 * Created At 2023/3/31 15:07
 * @author llh
 */
interface PermissionService {

    fun all(): List<Permission>

    fun addByDto(dto: PermissionAddDto): Boolean

    fun updateByDto(dto: PermissionUpdateDto): Boolean

    fun codeExist(code: String, notId: Int?): Boolean

    fun deleteByIds(ids: IdDto): Boolean
    fun idsInDb(idInput: List<Int>): List<Int>

    /**
     * 根据[roleIds]查出所有权限数据的代号
     */
    fun codeList(roleIds: List<Int>): List<String>

    fun pageQuery(page: PageDto, query: PermissionQueryDto?): Page<Permission>
}