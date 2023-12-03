package io.github.llh4github.kinoapi.model.auth

import io.github.llh4github.kinoapi.model.BaseModel
import org.babyfish.jimmer.sql.*

/**
 *
 * Created At 2023/12/1 21:15
 * @author llh
 */
@Entity
@Table(name = "auth_role")
interface Role : BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val roleId: Int

    val name: String

    val code: String

    @ManyToMany
    @JoinTable(
        name = "auth_user_role_link",
        joinColumnName = "role_id",
        inverseJoinColumnName = "user_id"
    )
    val users: List<User>
}