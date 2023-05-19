package com.jihulab.llh4gitlab.kinoapi.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 *
 * Created At 2023/4/14 16:32
 * @author llh
 */
open class IdDto {
    @Schema(title = "数据ID列表")
    var ids: List<Int> = emptyList()
}
