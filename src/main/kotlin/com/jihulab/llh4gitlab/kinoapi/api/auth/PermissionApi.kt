package com.jihulab.llh4gitlab.kinoapi.api.auth

import com.jihulab.llh4gitlab.kinoapi.api.BaseApi
import com.jihulab.llh4gitlab.kinoapi.contanst.ErrorCode
import com.jihulab.llh4gitlab.kinoapi.dto.JsonWrapper
import com.jihulab.llh4gitlab.kinoapi.dto.auth.PermissionAddDto
import com.jihulab.llh4gitlab.kinoapi.service.auth.PermissionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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

    @Operation(summary = "添加权限数据")
    @PostMapping("add")
    fun addByDto(
        @RequestBody @Valid dto: PermissionAddDto
    ): JsonWrapper<Boolean> {
        val has = permissionService.codeExist(dto.code)
        if (has) {
            return error(ErrorCode.DUPLICATE)
        }
        fillCreateInfo(dto)
        val saved = permissionService.addByDto(dto)
        return ok(saved)
    }
}