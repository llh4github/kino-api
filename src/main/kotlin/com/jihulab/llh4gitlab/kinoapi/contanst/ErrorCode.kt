package com.jihulab.llh4gitlab.kinoapi.contanst
/**
 * 系统错误码设计
 * Created At 2023/3/19 10:02
 * @author llh
 */
enum class ErrorCode(val code: Int, val msg: String) {
    OK(1, "OK"),
    AUTH(1001, "权限认证模块出错"),
    UNKNOWN_ERROR(9999, "应用未知错误"),
}
