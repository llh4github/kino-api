package com.github.llh4github.kinoapi.api.inner

import com.github.llh4github.kinoapi.api.BaseApi
import com.github.llh4github.kinoapi.dto.JsonWrapper
import com.github.llh4github.kinoapi.dto.inner.OrganizationAddDto
import com.github.llh4github.kinoapi.model.inner.Organization
import com.github.llh4github.kinoapi.service.inner.OrganizationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

/**
 *
 * Created At 2023/4/6 19:18
 * @author llh
 */
@RestController
@Tag(name = "组织数据操作接口")
@RequestMapping("inner/organization")
class OrganizationApi(
    private val organizationService: OrganizationService
) : BaseApi() {

    @PostMapping
    @Operation(summary = "添加数据")
    fun addByDto(
        @RequestBody @Valid dto: OrganizationAddDto
    ): JsonWrapper<Boolean> {
        organizationService.addByDto(dto)
        return ok(true)
    }


    @GetMapping("tree")
    @Operation(summary = "获取树形结构数据")
    fun tree(id: Int): JsonWrapper<Organization> {
        val tree = organizationService.findTree(id)
        return ok(tree)
    }
}