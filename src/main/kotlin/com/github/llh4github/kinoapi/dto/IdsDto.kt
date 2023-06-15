package com.github.llh4github.kinoapi.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min

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

open class IdDto {
    @get:Min(value = 0)
    @Schema(title = "数据ID", minimum = "1")
    var id: Int = -99
}