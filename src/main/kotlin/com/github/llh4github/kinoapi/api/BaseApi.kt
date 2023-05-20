package com.github.llh4github.kinoapi.api

import cn.dev33.satoken.stp.StpUtil
import com.jihulab.llh4gitlab.kinoapi.contanst.ErrorCode
import com.jihulab.llh4gitlab.kinoapi.dto.BaseDto
import com.jihulab.llh4gitlab.kinoapi.dto.JsonWrapper
import java.time.LocalDateTime

/**
 *
 * Created At 2023/3/28 21:58
 * @author llh
 */
abstract class BaseApi {
    /**
     * 正常响应
     */
    fun <T> ok(data: T? = null, msg: String = "OK"): JsonWrapper<T> {
        return JsonWrapper(msg = msg, data = data)
    }

    /**
     * 错误响应
     */
    fun <T> error(code: Int, msg: String, data: T? = null): JsonWrapper<T> {
        return JsonWrapper(code, msg, data)
    }

    fun <T> error(code: ErrorCode, msg: String? = null, data: T? = null): JsonWrapper<T> {
        val str = msg ?: code.msg
        return JsonWrapper(code.code, str, data)
    }

    fun <T : BaseDto> fillCreateInfo(model: T) {
        val now = LocalDateTime.now()
        model.createdTime = now
        model.updatedTime = now
        model.createdBy = StpUtil.getLoginIdAsInt()
    }

    fun <T : BaseDto> fillUpdateInfo(model: T) {
        val now = LocalDateTime.now()
        model.updatedTime = now
        model.updatedBy = StpUtil.getLoginIdAsInt()
    }
}