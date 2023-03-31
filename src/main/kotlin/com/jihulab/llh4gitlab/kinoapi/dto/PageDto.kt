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
        // 处理首页
        val p = if (page <= 1) 0 else page
        return PageRequest.of(p, size)
    }
}

