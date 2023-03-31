package com.jihulab.llh4gitlab.kinoapi.dto.convert

import com.jihulab.llh4gitlab.kinoapi.dto.RoleAddDto
import com.jihulab.llh4gitlab.kinoapi.model.RoleInput
import org.mapstruct.Mapper

/**
 *
 * Created At 2023/3/28 21:51
 * @author llh
 */
@Mapper
interface RoleDtoConvert {
    fun toDbInput(dto: RoleAddDto): RoleInput
}