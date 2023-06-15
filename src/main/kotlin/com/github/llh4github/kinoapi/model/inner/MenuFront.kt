package com.github.llh4github.kinoapi.model.inner

import com.github.llh4github.kinoapi.dto.convert.InnerConvert
import com.github.llh4github.kinoapi.model.BaseModel
import com.github.llh4github.kinoapi.model.BaseModelInput
import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.Input
import org.babyfish.jimmer.sql.*
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher

/**
 *
 * Created At 2023/5/23 14:48
 * @author llh
 */
@Entity
@Table(name = "inner_menu")
@Schema(title = "前端菜单数据")
interface MenuFront : BaseModel {

    @get:Schema(title = "名称")
    val name: String

    @get:Schema(title = "路由路径")
    val router: String

    @get:Schema(title = "上级菜单ID", nullable = true)
    val parentId: Int?

    @get:Schema(hidden = true)
    @ManyToOne
    @OnDissociate(value = DissociateAction.DELETE)
    val parent: MenuFront?

    @OneToMany(mappedBy = "parent")
    val children: List<MenuFront>
}

data class MenuFrontInput(
    val name: String,
    val router: String,
    val parentId: Int? = null,
    val children: List<MenuFrontInput>?
) : BaseModelInput(), Input<MenuFront> {

    override fun toEntity(): MenuFront {
        return InnerConvert.menu.toModel(this)
    }
}

object MenuFetcher {
    val TREE_FETCH = newFetcher(MenuFront::class).by {
        allScalarFields()
        children({ recursive() }) {
            allScalarFields()
        }
    }
    val SON_ID_FETCH = newFetcher(MenuFront::class).by {
        children({ recursive() }) {}
    }
}