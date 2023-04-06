package com.jihulab.llh4gitlab.kinoapi.service.auth

import com.jihulab.llh4gitlab.kinoapi.dto.auth.PermissionAddDto

/**
 *
 * Created At 2023/3/31 15:07
 * @author llh
 */
interface PermissionService {

    fun addByDto(dto: PermissionAddDto): Boolean

    fun codeExist(code: String): Boolean

    fun idsInDb(idInput: List<Int>): List<Int>

    /**
     * 根据[roleIds]查出所有权限数据的代号
     */
    fun codeList(roleIds: List<Int>): List<String>
}