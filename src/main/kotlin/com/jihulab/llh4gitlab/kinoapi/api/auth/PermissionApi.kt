package com.jihulab.llh4gitlab.kinoapi.api.auth

import com.jihulab.llh4gitlab.kinoapi.api.BaseApi
import com.jihulab.llh4gitlab.kinoapi.contanst.ErrorCode
import com.jihulab.llh4gitlab.kinoapi.dto.IdDto
import com.jihulab.llh4gitlab.kinoapi.dto.JsonWrapper
import com.jihulab.llh4gitlab.kinoapi.dto.PageDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.PermissionAddDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.PermissionQueryDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.PermissionUpdateDto
import com.jihulab.llh4gitlab.kinoapi.model.auth.Permission
import com.jihulab.llh4gitlab.kinoapi.service.auth.PermissionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*

/**
 *
 * Created At 2023/3/31 15:14
 * @author llh
 */
@Tag(name = "权限数据接口")
@RestController
@RequestMapping("auth/permission")
class PermissionApi(
    private val permissionService: PermissionService
) : BaseApi() {

    @Operation(summary = "所有权限数据")
    @GetMapping("all")
    fun all(): JsonWrapper<List<Permission>> {
        val list = permissionService.all()
        return ok(list)
    }

    @Operation(summary = "添加权限数据")
    @PostMapping
    fun addByDto(
        @RequestBody @Valid dto: PermissionAddDto
    ): JsonWrapper<Boolean> {
        val has = permissionService.codeExist(dto.code, null)
        if (has) {
            return error(ErrorCode.DUPLICATE)
        }
        fillCreateInfo(dto)
        val saved = permissionService.addByDto(dto)
        return ok(saved)
    }

    @Operation(summary = "修改权限数据")
    @PutMapping
    fun updateByDto(
        @RequestBody @Valid dto: PermissionUpdateDto
    ): JsonWrapper<Boolean> {
        val rs = permissionService.updateByDto(dto)
        return ok(rs)
    }

    @Operation(summary = "按ID删除数据")
    @DeleteMapping
    fun deleteById(
        @RequestBody @Valid dto: IdDto
    ): JsonWrapper<Boolean> {
        val rs = permissionService.deleteByIds(dto)
        return ok(rs)
    }

    @Operation(summary = "检查代码是否存在")
    @GetMapping("exist")
    fun codeExist(code: String, notId: Int?): JsonWrapper<Boolean> {
        val rs = permissionService.codeExist(code, notId)
        return ok(rs)
    }

    @Operation(summary = "分页查询")
    @GetMapping("page")
    fun pageQuery(
        page: PageDto, query: PermissionQueryDto?
    ): JsonWrapper<Page<Permission>> {
        val rs = permissionService.pageQuery(page, query)
        return ok(rs)
    }
}