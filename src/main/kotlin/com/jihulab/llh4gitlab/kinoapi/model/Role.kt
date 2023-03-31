package com.jihulab.llh4gitlab.kinoapi.model

import org.babyfish.jimmer.Input
import org.babyfish.jimmer.sql.Column
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.ManyToMany
import org.babyfish.jimmer.sql.Table
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

/**
 *
 * Created At 2023/3/28 20:53
 * @author llh
 */
@Entity
@Table(name = "auth_role")
interface Role : BaseModel {
    val code: String

    @Column(name = "name")
    val name: String

    @ManyToMany(mappedBy = "roles")
    val users: List<User>
}

data class RoleInput(
    var code: String? = null,
    var name: String? = null,
) : BaseModelInput(), Input<Role> {
    override fun toEntity(): Role = CONVERTER.toModel(this)

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }

    @Mapper
    internal interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toModel(input: RoleInput): Role
    }
}
