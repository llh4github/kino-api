package io.github.llh4github.kinoapi.service

import io.github.llh4github.kinoapi.dao.BaseDao
import io.github.llh4github.kinoapi.model.BaseModel

/**
 *
 * Created At 2023/12/3 15:26
 * @author llh
 */
interface BaseService<M : BaseModel> {
    fun findById(id: Int): M?
}
