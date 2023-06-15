package com.github.llh4github.kinoapi.dto.convert

import org.mapstruct.factory.Mappers

/**
 *转换方法统一调用类
 *
 * Created At 2023/3/28 21:52
 * @author llh
 */
object DtoConvert {
    val role: RoleDtoConvert = Mappers.getMapper(RoleDtoConvert::class.java)
    val user: UserDtoConvert = Mappers.getMapper(UserDtoConvert::class.java)
    val permission: PermissionDtoConvert = Mappers.getMapper(PermissionDtoConvert::class.java)
    val insideUrl = Mappers.getMapper(InsideUrlConvert::class.java)
    val organization = Mappers.getMapper(OrganizationConvert::class.java)
}

object InnerConvert {
    val menu: MenuFrontConvert = Mappers.getMapper(MenuFrontConvert::class.java)
}