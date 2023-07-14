package com.github.llh4github.kinoapi.dto.auth

import com.github.llh4github.kinoapi.contanst.enums.UserStatusEnums
import com.github.llh4github.kinoapi.dto.IdsDto
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

/**
 *
 *
 * Created At 2023/5/17 15:45
 * @author llh
 */
data class UserStatusUpdateDto(
    val status: UserStatusEnums
) : IdsDto()
