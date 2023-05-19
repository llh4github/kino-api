package com.jihulab.llh4gitlab.kinoapi.api.auth

import cn.dev33.satoken.stp.StpUtil
import com.jihulab.llh4gitlab.kinoapi.api.BaseApi
import com.jihulab.llh4gitlab.kinoapi.contanst.ErrorCode
import com.jihulab.llh4gitlab.kinoapi.dto.IdDto
import com.jihulab.llh4gitlab.kinoapi.dto.JsonWrapper
import com.jihulab.llh4gitlab.kinoapi.dto.PageDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.*
import com.jihulab.llh4gitlab.kinoapi.model.auth.User
import com.jihulab.llh4gitlab.kinoapi.service.auth.RoleService
import com.jihulab.llh4gitlab.kinoapi.service.auth.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*

/**
 *
 * Created At 2023/3/29 19:15
 * @author llh
 */
@RestController
@RequestMapping("auth/user")
@Tag(name = "用户数据操作接口")
class UserApi(
    private val userService: UserService,
    private val roleService: RoleService,
) : BaseApi() {

    @Operation(summary = "添加用户数据")
    @PostMapping
    fun addByDto(@RequestBody @Valid dto: UserAddDto): JsonWrapper<Boolean> {
        fillCreateInfo(dto)
        if (userService.isExistUsername(dto.username, null)) {
            return error(ErrorCode.AUTH, "用户名已存在")
        }
        if (dto.roleIds.isNotEmpty()) {
            dto.roleIds = roleService.idsInDb(dto.roleIds)
        }
        val saved = userService.addByDto(dto)
        return ok(saved)
    }

    @Operation(summary = "修改用户信息")
    @PutMapping
    fun updateByDto(
        @RequestBody @Valid dto: UserUpdateDto
    ): JsonWrapper<Boolean> {
        fillUpdateInfo(dto)
        val rs = userService.updateByDto(dto)
        return ok(rs)
    }

    @Operation(summary = "检查用户名是否存在")
    @GetMapping("exist")
    fun checkUsername(username: String, notId: Int?): JsonWrapper<Boolean> {
        val rs = userService.isExistUsername(username, notId)
        return ok(rs)
    }

    @Operation(summary = "分页查询")
    @GetMapping("page")
    fun pageQuery(page: PageDto, dto: UserQueryDto? = null): JsonWrapper<Page<User>> {
        val pageObj = userService.pageQuery(page, dto)
        return ok(pageObj)
    }

    @Operation(summary = "更新用户状态")
    @PutMapping("status")
    fun updateStatus(
        @RequestBody @Valid dto: UserStatusUpdateDto
    ): JsonWrapper<Int> {
        val rs = userService.updateUserStatus(dto)
        return ok(rs)
    }

    @Operation(summary = "更新用户状态为禁用")
    @PutMapping("status/disable")
    fun updateStatusDisable(
        @RequestBody @Valid ids: IdDto
    ): JsonWrapper<Int> {
        val dto = UserStatusUpdateDto(0)
        dto.ids = ids.ids
        val rs = userService.updateUserStatus(dto)
        return ok(rs)
    }

    @Operation(summary = "当前用户信息")
    @GetMapping("info/me")
    fun userInfo(): JsonWrapper<UserInfoDto> {
        val userId = StpUtil.getLoginIdAsInt()
        val info = userService.fetchInfo(userId)
        return ok(info)
    }

    @GetMapping("{id}/detail")
    @Operation(summary = "带有角色信息的详情")
    fun detailWithRole(
        @PathVariable id: Int
    ): JsonWrapper<User> {
        val rs = userService.detailWithRole(id)
        return ok(rs)
    }

    @Operation(summary = "更新密码")
    @PutMapping("update/pwd")
    fun updatePwd(
        @RequestBody @Valid dto: UserPwdUpdateDto
    ): JsonWrapper<Int> {
        fillUpdateInfo(dto)
        val rs = userService.updatePwd(dto)
        return ok(rs)
    }
}