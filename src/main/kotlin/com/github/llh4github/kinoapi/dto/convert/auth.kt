package com.github.llh4github.kinoapi.dto.convert

import com.github.llh4github.kinoapi.dto.auth.*
import com.github.llh4github.kinoapi.model.auth.PermissionInput
import com.github.llh4github.kinoapi.model.auth.RoleInput
import com.github.llh4github.kinoapi.model.auth.UserInput
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping

/**
 *
 * Created At 2023/3/31 14:55
 * @author llh
 */
@Mapper
interface RoleDtoConvert {
    fun toDbInput(dto: RoleAddDto): RoleInput
    fun toDbInput(dto: RoleUpdateDto): RoleInput
}

@Mapper
interface UserDtoConvert {
    fun toDbInput(dto: UserAddDto): UserInput
    fun toDbInput(dto: UserUpdateDto): UserInput
}

@Mapper
interface PermissionDtoConvert {
    fun toDbInput(dto: PermissionAddDto): PermissionInput
    fun toDbInput(dto: PermissionUpdateDto): PermissionInput
}