package com.github.llh4github.kinoapi.dto.auth

import com.jihulab.llh4gitlab.kinoapi.dto.IdDto
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

/**
 *
 *
 * Created At 2023/5/17 15:45
 * @author llh
 */
data class UserStatusUpdateDto(
    @get:Min(value = 0)
    @get:Max(value = 99)
    val status: Int
) : IdDto()
