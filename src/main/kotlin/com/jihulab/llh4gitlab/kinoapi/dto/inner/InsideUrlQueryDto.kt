package com.jihulab.llh4gitlab.kinoapi.dto.inner

import com.jihulab.llh4gitlab.kinoapi.contanst.HttpMethodEnum
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 * Created At 2023/4/4 18:30
 * @author llh
 */
data class InsideUrlQueryDto(

    @get:Schema(title = "请求方法")
    val method: HttpMethodEnum?,

    @get:Schema(title = "url")
    val url: String?,

    @get:Schema(title = "备注")
    val remark: String?,
)