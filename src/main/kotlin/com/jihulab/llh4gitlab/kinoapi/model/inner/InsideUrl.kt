package com.jihulab.llh4gitlab.kinoapi.model.inner

import com.jihulab.llh4gitlab.kinoapi.contanst.HttpMethodEnum
import com.jihulab.llh4gitlab.kinoapi.model.BaseModel
import com.jihulab.llh4gitlab.kinoapi.model.BaseModelInput
import com.jihulab.llh4gitlab.kinoapi.model.auth.Permission
import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.Input
import org.babyfish.jimmer.sql.*
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

/**
 *
 * Created At 2023/4/4 16:09
 * @author llh
 */
@Entity
@Table(name = "inner_inside_url")
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

    @ManyToMany
    @JoinTable(
        name = "link_url_permission",
        inverseJoinColumnName = "permission_id",
        joinColumnName = "url_id"
    )
    val permissions: List<Permission>

    @IdView("permissions")
    val permissionIds: List<Int>
}

data class InsideUrlInput(
    val method: HttpMethodEnum,
    val url: String,
    val remark: String,
    val permissionOrMode: Boolean,
    val permissionIds: MutableList<Int> = mutableListOf(),
) : BaseModelInput(), Input<InsideUrl> {

    override fun toEntity() = CONVERTER.toModel(this)

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }

    @Mapper
    internal interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toModel(input: InsideUrlInput): InsideUrl
    }
}