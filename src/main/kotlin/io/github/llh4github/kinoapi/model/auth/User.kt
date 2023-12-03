package io.github.llh4github.kinoapi.model.auth

import com.fasterxml.jackson.annotation.JsonIgnore
import io.github.llh4github.kinoapi.model.BaseModel
import org.babyfish.jimmer.sql.*

/**
 *
 * Created At 2023/11/30 21:43
 * @author llh
 */
@Entity
@Table(name = "auth_user")
interface User : BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Int

    @Key
    val username: String

    @get:JsonIgnore
    val password: String

    @ManyToMany
    @JoinTable(
        name = "auth_user_role_link",
        joinColumnName = "user_id",
        inverseJoinColumnName = "role_id"
    )
    val roles: List<Role>
}