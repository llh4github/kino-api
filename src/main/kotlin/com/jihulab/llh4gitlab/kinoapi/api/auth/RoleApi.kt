package com.jihulab.llh4gitlab.kinoapi.api.auth

import com.jihulab.llh4gitlab.kinoapi.api.BaseApi
import com.jihulab.llh4gitlab.kinoapi.contanst.ErrorCode
import com.jihulab.llh4gitlab.kinoapi.dto.JsonWrapper
import com.jihulab.llh4gitlab.kinoapi.dto.PageDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.RoleAddDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.RoleQueryDto
import com.jihulab.llh4gitlab.kinoapi.model.auth.Role
import com.jihulab.llh4gitlab.kinoapi.service.auth.PermissionService
import com.jihulab.llh4gitlab.kinoapi.service.auth.RoleService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 *
 * Created At 2023/3/28 20:56
 * @author llh
 */
@Tag(name = "角色数据操作接口")
@RestController
@RequestMapping("auth/role")
class RoleApi(
    private val service: RoleService,
    private val permissionService: PermissionService,
) : BaseApi() {

    @GetMapping("page")
    @Operation(summary = "角色分页查询接口")
    fun page(page: PageDto, query: RoleQueryDto?): JsonWrapper<Page<Role>> {
        val pageObj = service.pageQuery(page, query)
        return ok(pageObj)
    }

    @PostMapping("add")
    @Operation(summary = "添加角色数据")
    fun addByVo(@RequestBody @Valid dto: RoleAddDto): JsonWrapper<Boolean> {
        if (service.hasCode(dto.code)) {
            return error(ErrorCode.DUPLICATE)
        }
        fillCreateInfo(dto)
        dto.permissionIds?.let {
            if (it.isNotEmpty()) {
                dto.permissionIds = permissionService.idsInDb(it)
            }
        }
        val saved = service.addByDto(dto)
        return ok(saved)
    }
}