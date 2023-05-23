package com.github.llh4github.kinoapi.repository.inner

import com.github.llh4github.kinoapi.model.inner.MenuFetcher
import com.github.llh4github.kinoapi.model.inner.MenuFront
import com.github.llh4github.kinoapi.model.inner.id
import com.github.llh4github.kinoapi.model.inner.parentId
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.isNull

/**
 *
 * Created At 2023/5/23 15:02
 * @author llh
 */
interface MenuFrontRepository : KRepository<MenuFront, Int> {

    /**
     * 根据某个节点ID查询树形数据
     */
    fun findTreeByRootId(id: Int): MenuFront? {
        return sql.createQuery(MenuFront::class) {
            where(table.id eq id)
            select(table.fetch(MenuFetcher.TREE_FETCH))
        }.fetchOneOrNull()
    }

    /**
     * 根据pId查询树形列表数据
     *
     * 查询某节点下所有的子孙节点(不含自身)
     */
    fun findTreeList(pId: Int? = null): List<MenuFront> {
        return sql.createQuery(MenuFront::class) {
            if (pId == null) {
                where(table.parentId.isNull())
            } else {
                where(table.parentId eq pId)
            }
            select(table.fetch(MenuFetcher.TREE_FETCH))
        }.execute()
    }

}