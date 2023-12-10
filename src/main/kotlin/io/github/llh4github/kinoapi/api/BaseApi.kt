package io.github.llh4github.kinoapi.api

import io.github.llh4github.kinoapi.dto.JsonWrapper
import io.github.llh4github.kinoapi.dto.RespMsg
import io.github.llh4github.kinoapi.dto.RespMsgEnums

/**
 *
 * Created At 2023/12/3 15:32
 * @author llh
 */
abstract class BaseApi {
    fun <T> ok(data: T? = null): JsonWrapper<T> {
        return JsonWrapper(RespMsgEnums.OK, data)
    }

    fun error(errorResp: RespMsg): JsonWrapper<Void> {
        return JsonWrapper(errorResp)
    }
}