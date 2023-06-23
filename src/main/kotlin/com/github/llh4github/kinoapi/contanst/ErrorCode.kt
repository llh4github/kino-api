package com.github.llh4github.kinoapi.contanst

/**
 * 错误码字段属性约束接口
 */
interface IErrorCode {
    val code: Int
    val msg: String
}

/**
 * 系统错误码设计
 * Created At 2023/3/19 10:02
 * @author llh
 */
enum class ErrorCode(
    override val code: Int,
    override val msg: String
) : IErrorCode {
    OK(1, "OK"),
    DUPLICATE(10, "数据已存在"),
    NOT_LOGIN(401, "用户未登录"),
    NO_PERMISSION(402, "无访问权限"),
    VALID(11, "字段验证未通过"),
    AUTH(1001, "权限认证模块出错"),
    USER_PWD_ERROR(1002, "用户或密码错误"),
    DB(8001, "数据操作失败"),
    UNKNOWN_ERROR(9999, "应用未知错误"),
}

