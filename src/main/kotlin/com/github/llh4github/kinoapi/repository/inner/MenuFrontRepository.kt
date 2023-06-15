package com.github.llh4github.kinoapi.repository.inner

import com.github.llh4github.kinoapi.model.inner.*
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.isNull
import org.babyfish.jimmer.sql.kt.ast.expression.like

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
     * 获取指定节点及其子孙节点ID
     */
    fun findSonIds(id: Int): List<Int> {
        return sql.createQuery(MenuFront::class) {
            where(table.id eq id)
            select(table.fetch(MenuFetcher.SON_ID_FETCH))
        }.execute()
            .flatMap {
                val list = it.children.map { ele -> ele.id }.toMutableList()
                list.add(it.id)
                list.toList()
            }.reversed()
    }

    /**
     * 根据pId查询树形列表数据
     *
     * 查询某节点下所有的子孙节点(不含自身)
     */
    fun findTreeList(pId: Int? = null, name: String? = null, router: String? = null): List<MenuFront> {
        return sql.createQuery(MenuFront::class) {
            if (pId == null) {
                where(table.parentId.isNull())
            } else {
                where(table.parentId eq pId)
            }
            name?.takeIf { it.isNotEmpty() }?.apply {
                where(table.name like this)
            }
            router?.takeIf { it.isNotEmpty() }?.apply {
                where(table.router like this)
            }
            select(table.fetch(MenuFetcher.TREE_FETCH))
        }.execute()
    }

}