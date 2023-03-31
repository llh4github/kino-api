package com.jihulab.llh4gitlab.kinoapi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.babyfish.jimmer.Input
import org.babyfish.jimmer.sql.*
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

/**
 *
 * Created At 2023/3/29 18:44
 * @author llh
 */
@Entity
@Table(name = "auth_user")
interface User : BaseModel {
    @Key
    val username: String

    @Column(name = "password")
    @get:JsonIgnore
    val password: String

    @ManyToMany
    @JoinTable(
        name = "link_user_role",
        joinColumnName = "user_id",
        inverseJoinColumnName = "role_id"
    )
    val roles: List<Role>

    @IdView("roles")
    val roleIds: List<Int>
}

data class UserInput(
    var username: String? = null,
    var password: String? = null,
    var roles: List<RoleInput> = emptyList(),
    var roleIds: List<Int> = emptyList(),
) : BaseModelInput(), Input<User> {
    override fun toEntity(): User = CONVERTER.toUser(this)

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }

    @Mapper
    internal interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toUser(input: UserInput): User
    }
}