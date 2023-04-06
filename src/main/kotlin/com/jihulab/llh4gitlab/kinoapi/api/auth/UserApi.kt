package com.jihulab.llh4gitlab.kinoapi.api.auth

import com.jihulab.llh4gitlab.kinoapi.api.BaseApi
import com.jihulab.llh4gitlab.kinoapi.contanst.ErrorCode
import com.jihulab.llh4gitlab.kinoapi.dto.JsonWrapper
import com.jihulab.llh4gitlab.kinoapi.dto.PageDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.UserAddDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.UserQueryDto
import com.jihulab.llh4gitlab.kinoapi.model.auth.User
import com.jihulab.llh4gitlab.kinoapi.service.auth.RoleService
import com.jihulab.llh4gitlab.kinoapi.service.auth.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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
    @PostMapping("add")
    fun addByDto(@RequestBody @Valid dto: UserAddDto): JsonWrapper<Boolean> {
        fillCreateInfo(dto)
        if (userService.isExistUsername(dto.username)) {
            return error(ErrorCode.AUTH, "用户名已存在")
        }
        if (dto.roleIds.isNotEmpty()) {
            dto.roleIds = roleService.idsInDb(dto.roleIds)
        }
        val saved = userService.addByDto(dto)
        return ok(saved)
    }

    @Operation(summary = "分页查询")
    @GetMapping("page")
    fun pageQuery(page: PageDto, dto: UserQueryDto? = null): JsonWrapper<Page<User>> {
        val pageObj = userService.pageQuery(page, dto)
        return ok(pageObj)
    }
}