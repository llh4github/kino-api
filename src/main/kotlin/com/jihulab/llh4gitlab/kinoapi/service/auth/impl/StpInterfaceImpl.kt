package com.jihulab.llh4gitlab.kinoapi.service.auth.impl

import cn.dev33.satoken.stp.StpInterface
import com.jihulab.llh4gitlab.kinoapi.service.auth.PermissionService
import com.jihulab.llh4gitlab.kinoapi.service.auth.RoleService
import org.springframework.stereotype.Service

/**
 *
 * Created At 2023/4/2 14:28
 * @author llh
 */
@Service
class StpInterfaceImpl(
    private val roleService: RoleService,
    private val permissionService: PermissionService
) : StpInterface {
    override fun getPermissionList(
        loginId: Any?, loginType: String?
    ): List<String> {
        if (loginId == null) return emptyList()
        val rIds = roleService.ids(loginId.toString().toInt())
        if (rIds.isEmpty()) return emptyList()
        return permissionService.codeList(rIds)
    }

    override fun getRoleList(
        loginId: Any?, loginType: String?
    ): List<String>? {
        if (loginId == null) return emptyList()
        return roleService.roleCodes(loginId.toString().toInt())
    }
}