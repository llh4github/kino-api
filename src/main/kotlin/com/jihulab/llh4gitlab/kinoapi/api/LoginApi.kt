package com.jihulab.llh4gitlab.kinoapi.api

import cn.dev33.satoken.stp.StpUtil
import com.jihulab.llh4gitlab.kinoapi.dto.JsonWrapper
import com.jihulab.llh4gitlab.kinoapi.dto.auth.LoginDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.LoginTokenDto
import com.jihulab.llh4gitlab.kinoapi.service.auth.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 *
 * Created At 2023/4/2 10:01
 * @author llh
 */
@Tag(name = "登录登出接口")
@RestController
class LoginApi(
    private val userService: UserService
) : BaseApi() {

    @Operation(summary = "登录接口")
    @PostMapping("login")
    fun login(
        @RequestBody @Valid dto: LoginDto
    ): JsonWrapper<LoginTokenDto> {
        val token = userService.login(dto)
        return ok(token)
    }

    @Operation(summary = "登出接口")
    @PostMapping("logout")
    fun logout(): JsonWrapper<Boolean> {
        StpUtil.logout()
        return ok(true)
    }
}