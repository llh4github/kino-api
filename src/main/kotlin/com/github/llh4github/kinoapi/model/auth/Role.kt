package com.github.llh4github.kinoapi.model.auth

import com.github.llh4github.kinoapi.model.BaseModel
import com.github.llh4github.kinoapi.model.BaseModelInput
import org.babyfish.jimmer.Input
import org.babyfish.jimmer.sql.*
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
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

    @ManyToMany
    @JoinTable(
        name = "link_role_permission",
        joinColumnName = "role_id",
        inverseJoinColumnName = "permission_id"
    )
    val permissions: List<Permission>

    @IdView("permissions")
    val permissionIds: List<Int>
}

data class RoleInput(
    var code: String? = null,
    var name: String? = null,
    val permissionIds: List<Int> = mutableListOf(),
    val permissions: List<PermissionInput> = mutableListOf(),
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

object RoleFetcher{
    val simpleFetch = newFetcher(Role::class).by {
        allScalarFields()
        permissions {
            code()
            name()
        }
    }
}