package com.github.llh4github.kinoapi.api.auth

import com.github.llh4github.kinoapi.api.BaseApi
import com.github.llh4github.kinoapi.contanst.ErrorCode
import com.github.llh4github.kinoapi.dto.IdsDto
import com.github.llh4github.kinoapi.dto.JsonWrapper
import com.github.llh4github.kinoapi.dto.PageDto
import com.github.llh4github.kinoapi.dto.auth.RoleAddDto
import com.github.llh4github.kinoapi.dto.auth.RoleQueryDto
import com.github.llh4github.kinoapi.dto.auth.RoleUpdateDto
import com.github.llh4github.kinoapi.model.auth.Role
import com.github.llh4github.kinoapi.service.auth.PermissionService
import com.github.llh4github.kinoapi.service.auth.RoleService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.babyfish.jimmer.client.FetchBy
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

    @GetMapping("all")
    @Operation(summary = "所有角色信息")
    fun all(): JsonWrapper<List<@FetchBy("simpleFetch") Role>> {
        val list = service.allSimple()
        return ok(list)
    }

    @Operation(summary = "带权限信息的详细数据")
    @GetMapping("{id}/detail")
    fun detailWithPermissionId(
        @PathVariable id: Int
    ): JsonWrapper<Role> {
        val rs = service.detail(id)
        return ok(rs)
    }

    @GetMapping("exist")
    @Operation(
        summary = "检查code是否存在",
        description = "返回true表示已存在",
        parameters = [
            Parameter(name = "code", description = "待检测的code值"),
            Parameter(name = "notId", description = "排除数据的ID，防止对已存在的数据进行验证", required = false),
        ]
    )
    fun exist(code: String, notId: Int?): JsonWrapper<Boolean> {
        val exist = service.existCode(code, notId)
        return ok(exist)
    }

    @DeleteMapping
    @Operation(summary = "删除角色数据")
    fun deleteByIds(@RequestBody @Valid ids: IdsDto): JsonWrapper<Boolean> {
        service.deleteByIds(ids)
        return ok(true)
    }

    @PostMapping
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

    @PutMapping
    @Operation(summary = "更新角色数据")
    fun updateByDto(
        @RequestBody @Valid dto: RoleUpdateDto
    ): JsonWrapper<Boolean> {
        fillUpdateInfo(dto)
        dto.permissionIds?.let {
            if (it.isNotEmpty()) {
                dto.permissionIds = permissionService.idsInDb(it)
            }
        }

        val updated: Boolean = service.updateByDto(dto)
        return ok(updated)
    }
}
