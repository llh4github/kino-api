package io.github.llh4github.kinoapi.dao

import io.github.llh4github.kinoapi.model.BaseModel
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.data.repository.NoRepositoryBean

/**
 *
 * Created At 2023/12/1 21:54
 * @author llh
 */
@NoRepositoryBean
interface BaseDao<M : BaseModel> : KRepository<M, Int> {

}