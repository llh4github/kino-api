package com.jihulab.llh4gitlab.kinoapi.dto.convert

import com.jihulab.llh4gitlab.kinoapi.dto.UserAddDto
import com.jihulab.llh4gitlab.kinoapi.model.UserInput
import org.mapstruct.Mapper

/**
 *
 * Created At 2023/3/29 18:50
 * @author llh
 */
@Mapper
interface UserDtoConvert {
    fun toUserInput(dto: UserAddDto): UserInput
}