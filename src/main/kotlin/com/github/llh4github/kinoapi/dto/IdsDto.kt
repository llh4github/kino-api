package com.github.llh4github.kinoapi.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 *
 * Created At 2023/4/14 16:32
 * @author llh
 */
open class IdsDto {
    @Schema(title = "数据ID列表")
    var ids: List<Int> = emptyList()
}
