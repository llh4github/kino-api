package com.github.llh4github.kinoapi.model.inner

import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.llh4github.kinoapi.model.BaseModel
import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.Formula
import org.babyfish.jimmer.sql.*
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher

/**
 *
 * Created At 2023/5/23 14:48
 * @author llh
 */
@Entity
@Table(name = "inner_menu_auth")
@Schema(title = "菜单权限")
interface MenuFront : BaseModel {

    @get:JsonIgnore
    @get:Schema(title = "标题")
    val title: String

    @get:Schema(title = "英文名称")
    val name: String

    @get:Schema(title = "路由路径")
    val path: String

    @get:JsonIgnore
    @get:Schema(title = "ICON名")
    val icon: String

    @get:JsonIgnore
    @get:Schema(title = "名次")
    val rank: Int

    @get:Schema(title = "上级菜单ID", nullable = true)
    val parentId: Int?

    @get:Schema(hidden = true)
    @ManyToOne
    @OnDissociate(value = DissociateAction.DELETE)
    val parent: MenuFront?

    @OneToMany(mappedBy = "parent")
    val children: List<MenuFront>

    @Formula(dependencies = ["title", "icon", "rank"])
    @get:Schema(title = "菜单元数据")
    val meta: MenuMeta
        get() {
            return MenuMeta(title, icon, rank)
        }

}

data class MenuMeta(

    @get:Schema(title = "标题")
    val title: String,

    @get:Schema(title = "ICON名")
    val icon: String,

    @get:Schema(title = "名次")
    val rank: Int,

    @get:Schema(title = "角色代号列表")
    val roles: MutableList<String> = mutableListOf(),

    @get:Schema(title = "权限代号列表")
    val auths: MutableList<String> = mutableListOf(),
) {
    fun addRoles(roles: List<String>): MenuMeta {
        this.roles.addAll(roles)
        return this
    }

    fun addAuths(auths: List<String>): MenuMeta {
        this.auths.addAll(auths)
        return this
    }
}

object MenuFetcher {
    val TREE_FETCH = newFetcher(MenuFront::class).by {
        allScalarFields()
        meta()
        children({ recursive() }) {
            allScalarFields()
        }
    }
    val SON_ID_FETCH = newFetcher(MenuFront::class).by {
        children({ recursive() }) {}
    }
}