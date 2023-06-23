package com.github.llh4github.kinoapi.model.auth

import com.github.llh4github.kinoapi.model.BaseModel
import com.github.llh4github.kinoapi.model.inner.InsideUrl
import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.Input
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.IdView
import org.babyfish.jimmer.sql.ManyToMany
import org.babyfish.jimmer.sql.Table


/**
 * 权限数据表
 * Created At 2023/3/31 14:31
 * @author llh
 */
@Entity
@Table(name = "auth_permission")
interface Permission : BaseModel {

    @get:Schema(title = "权限代号")
    val code: String

    @get:Schema(title = "权限名")
    val name: String

    @ManyToMany(mappedBy = "permissions")
    val roles: List<Role>

    @IdView("roles")
    val roleIds: List<Int>

    @ManyToMany(mappedBy = "permissions")
    val urls:List<InsideUrl>
}
