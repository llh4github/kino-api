package com.github.llh4github.kinoapi.contanst

import cn.dev33.satoken.router.SaHttpMethod

/**
 *
 *
 * Created At 2023/4/4 16:25
 * @author llh
 */
enum class HttpMethodEnum {

    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE, CONNECT,

    /**
     * 未选择Http方法，通常是模糊匹配url时用
     */
    NONE;

    /**
     * 将请求方法转换为sa-token框架的枚举类
     */
    fun toSaHttpMethod(): SaHttpMethod {
        return when (this) {
            GET -> SaHttpMethod.GET
            HEAD -> SaHttpMethod.HEAD
            POST -> SaHttpMethod.POST
            PUT -> SaHttpMethod.PUT
            PATCH -> SaHttpMethod.PATCH
            DELETE -> SaHttpMethod.DELETE
            OPTIONS -> SaHttpMethod.OPTIONS
            TRACE -> SaHttpMethod.TRACE
            CONNECT -> SaHttpMethod.CONNECT
            NONE -> SaHttpMethod.ALL
        }
    }
}
