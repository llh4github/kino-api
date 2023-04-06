package com.jihulab.llh4gitlab.kinoapi.dto.convert

import com.jihulab.llh4gitlab.kinoapi.dto.auth.PermissionAddDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.RoleAddDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.UserAddDto
import com.jihulab.llh4gitlab.kinoapi.model.auth.PermissionInput
import com.jihulab.llh4gitlab.kinoapi.model.auth.RoleInput
import com.jihulab.llh4gitlab.kinoapi.model.auth.UserInput
import org.mapstruct.Mapper

/**
 *
 * Created At 2023/3/31 14:55
 * @author llh
 */
@Mapper
interface RoleDtoConvert {
    fun toDbInput(dto: RoleAddDto): RoleInput
}

@Mapper
interface UserDtoConvert {
    fun toDbInput(dto: UserAddDto): UserInput
}

@Mapper
interface PermissionDtoConvert {
    fun toDbInput(dto: PermissionAddDto): PermissionInput
}