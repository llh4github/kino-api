package com.github.llh4github.kinoapi.repository.inner

import com.github.llh4github.kinoapi.model.inner.Organization
import com.github.llh4github.kinoapi.model.inner.by
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher

/**
 *
 * Created At 2023/4/6 15:20
 * @author llh
 */
interface OrganizationRepository : KRepository<Organization, Int> {

    /**
     * 查询树形结构数据
     */
    fun findTree(id: Int): Organization? {
        return findNullable(id, newFetcher(Organization::class).by {
            allScalarFields()
            children({
                recursive()
            }) {
                allScalarFields()
            }
        })

    }
}