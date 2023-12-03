package io.github.llh4github.kinoapi.api.auth

import io.github.llh4github.kinoapi.api.BaseApi
import io.github.llh4github.kinoapi.dto.JsonWrapper
import io.github.llh4github.kinoapi.model.auth.User
import io.github.llh4github.kinoapi.model.auth.dto.UserSimpleView
import io.github.llh4github.kinoapi.service.auth.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.babyfish.jimmer.client.FetchBy
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * Created At 2023/12/3 15:53
 * @author llh
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("auth/user")
class UserApi(
    private val service: UserService
) : BaseApi() {

    @GetMapping("{userId}")
    @Operation(summary = "根据id获取数据")
    fun id(@PathVariable userId: Int): JsonWrapper<@FetchBy(
        value = "UserSimpleView",
        ownerType = UserSimpleView::class
    ) User> {
        val model = service.findById(userId)
        return ok(model)
    }
}