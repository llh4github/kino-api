package com.github.llh4github.kinoapi.dto.inner

import com.github.llh4github.kinoapi.dto.BaseDto
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 *
 * Created At 2023/4/6 19:12
 * @author llh
 */
data class OrganizationAddDto(
    @get:Schema(title = "组织名")
    val name: String,
    @field:Schema(title = "上级ID")
    val pId: Int?,
) : BaseDto()
