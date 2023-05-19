package com.jihulab.llh4gitlab.kinoapi.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.domain.PageRequest

/**
 *
 * Created At 2023/3/28 21:46
 * @author llh
 */
@Schema(title = "分页参数")
data class PageDto(
    @Schema(title = "当前页")
    val page: Int = 0,
    @Schema(title = "每页大小")
    val size: Int = 10,
) {
    fun toPageRequest(): PageRequest {
        // 前端页码比后台多1
        val p1 = page - 1
        val p = if (p1 < 0) 0 else p1
        return PageRequest.of(p, size)
    }
}

