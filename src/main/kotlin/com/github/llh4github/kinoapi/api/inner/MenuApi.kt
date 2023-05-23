package com.github.llh4github.kinoapi.api.inner

import com.github.llh4github.kinoapi.api.BaseApi
import com.github.llh4github.kinoapi.dto.JsonWrapper
import com.github.llh4github.kinoapi.dto.inner.MenuAddDto
import com.github.llh4github.kinoapi.model.inner.MenuFront
import com.github.llh4github.kinoapi.service.inner.MenuService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * Created At 2023/5/23 17:51
 * @author llh
 */
@Tag(name = "动态菜单数据接口")
@RestController
@RequestMapping("inner/menu")
class MenuApi(
    private val service: MenuService
) : BaseApi() {
    @Operation(summary = "获取树形列表数据")
    @GetMapping("treeList")
    fun treeList(parentId: Int?): JsonWrapper<List<MenuFront>> {
        val list = service.treeList(parentId)
        return ok(list)
    }

    @Operation(summary = "获取树形数据")
    @GetMapping("tree")
    fun tree(id: Int): JsonWrapper<MenuFront> {
        val tree = service.tree(id)
        return ok(tree)
    }
    @PostMapping
    @Operation(summary = "添加菜单数据")
    fun add(
       @RequestBody @Valid dto:MenuAddDto
    ): JsonWrapper<Boolean> {
        fillCreateInfo(dto)
        val rs = service.addByDto(dto)
        return ok(rs)
    }
}