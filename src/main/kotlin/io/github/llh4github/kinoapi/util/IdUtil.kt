package io.github.llh4github.kinoapi.util

import com.github.yitter.contract.IdGeneratorOptions
import com.github.yitter.idgen.YitIdHelper

/**
 *
 * Created At 2023/12/10 15:57
 * @author llh
 */
object IdUtil {
    init {
        val options = IdGeneratorOptions(2)
        YitIdHelper.setIdGenerator(options)
    }

    fun nextId(): Long {
        return YitIdHelper.nextId()
    }
}