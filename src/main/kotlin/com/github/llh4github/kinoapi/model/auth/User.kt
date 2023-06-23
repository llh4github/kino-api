package com.github.llh4github.kinoapi.model.auth

import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.llh4github.kinoapi.model.BaseModel
import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.sql.*


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

    @get:Schema(title = "用户状态")
    val status: Int

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
