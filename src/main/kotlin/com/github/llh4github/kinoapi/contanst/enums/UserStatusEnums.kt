package com.github.llh4github.kinoapi.contanst.enums

import org.babyfish.jimmer.sql.EnumItem
import org.babyfish.jimmer.sql.EnumType

/**
 * 用户状态枚举
 *
 * Created At 2023/7/12 16:02
 * @author llh
 */
@EnumType(EnumType.Strategy.ORDINAL)
enum class UserStatusEnums {

    @EnumItem(ordinal = 0)
    INACTIVE,

    @EnumItem(ordinal = 5)
    NORMAL,

    @EnumItem(ordinal = 9)
    BANNED,
}