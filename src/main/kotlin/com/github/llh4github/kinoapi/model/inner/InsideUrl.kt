package com.github.llh4github.kinoapi.model.inner

import com.github.llh4github.kinoapi.contanst.HttpMethodEnum
import com.github.llh4github.kinoapi.model.BaseModel
import com.github.llh4github.kinoapi.model.auth.Permission
import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.sql.*


/**
 *
 * Created At 2023/4/4 16:09
 * @author llh
 */
@Entity
@Table(name = "inner_inside_url")
@Deprecated("改用 menu ")
interface InsideUrl : BaseModel {
    @get:Schema(title = "请求方法")
    val method: HttpMethodEnum

    @Key
    @get:Schema(title = "url", description = "必须以/开头")
    val url: String

    @get:Schema(title = "备注")
    val remark: String

    @get:Schema(title = "权限校验模式")
    val permissionOrMode: Boolean

//    @ManyToMany
//    @JoinTable(
//        name = "link_url_permission",
//        inverseJoinColumnName = "permission_id",
//        joinColumnName = "url_id"
//    )
//    val permissions: List<Permission>
//
//    @IdView("permissions")
//    val permissionIds: List<Int>
}
