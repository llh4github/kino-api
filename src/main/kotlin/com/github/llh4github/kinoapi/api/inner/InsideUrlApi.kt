package com.github.llh4github.kinoapi.api.inner

import com.jihulab.llh4gitlab.kinoapi.api.BaseApi
import com.jihulab.llh4gitlab.kinoapi.contanst.ErrorCode
import com.jihulab.llh4gitlab.kinoapi.dto.IdDto
import com.jihulab.llh4gitlab.kinoapi.dto.JsonWrapper
import com.jihulab.llh4gitlab.kinoapi.dto.PageDto
import com.jihulab.llh4gitlab.kinoapi.dto.inner.InsideUrlAddDto
import com.jihulab.llh4gitlab.kinoapi.dto.inner.InsideUrlQueryDto
import com.jihulab.llh4gitlab.kinoapi.dto.inner.InsideUrlUpdateDto
import com.jihulab.llh4gitlab.kinoapi.model.inner.InsideUrl
import com.jihulab.llh4gitlab.kinoapi.service.auth.PermissionService
import com.jihulab.llh4gitlab.kinoapi.service.inner.InsideUrlService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*

/**
 *
 * Created At 2023/4/4 17:00
 * @author llh
 */
@RestController
@RequestMapping("inner/insideUrl")
@Tag(name = "应用内部url数据接口")
class InsideUrlApi(
    private val insideUrlService: InsideUrlService,
    private val permissionService: PermissionService,
) : BaseApi() {
    @Operation(summary = "添加数据")
    @PostMapping
    fun addByDto(
        @RequestBody @Valid dto: InsideUrlAddDto
    ): JsonWrapper<Boolean> {
        dto.permissionIds = permissionService.idsInDb(dto.permissionIds)
        if (insideUrlService.urlExist(dto.url)) {
            return error(ErrorCode.DUPLICATE)
        }
        fillCreateInfo(dto)
        val saved = insideUrlService.addByDto(dto)
        return ok(saved)
    }

    @Operation(summary = "更新数据")
    @PutMapping
    fun updateByDto(
        @RequestBody @Valid dto: InsideUrlUpdateDto
    ): JsonWrapper<Boolean> {
        dto.permissionIds = permissionService.idsInDb(dto.permissionIds)
        fillUpdateInfo(dto)
        val saved = insideUrlService.updateByDto(dto)
        return ok(saved)
    }

    @DeleteMapping
    @Operation(summary = "按ID删除数据")
    fun deleteByIds(
        @RequestBody @Valid dto: IdDto
    ): JsonWrapper<Boolean> {
        insideUrlService.deleteById(dto)
        return ok(true)
    }

    @GetMapping("page")
    @Operation(summary = "分页查询")
    fun pageQuery(
        page: PageDto, query: InsideUrlQueryDto
    ): JsonWrapper<Page<InsideUrl>> {
        val rs: Page<InsideUrl> = insideUrlService.pageQuery(page, query)
        return ok(rs)
    }
}