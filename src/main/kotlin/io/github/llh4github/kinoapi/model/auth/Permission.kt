package io.github.llh4github.kinoapi.model.auth

import io.github.llh4github.kinoapi.model.BaseModel
import org.babyfish.jimmer.sql.*

/**
 *
 * Created At 2023/12/10 11:19
 * @author llh
 */
@Entity
@Table(name = "auth_permission")
interface Permission : BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val permissionId: Int

    val name: String

    @Key
    val code: String

    @ManyToMany(mappedBy = "permissions")
    val roles: List<Role>
}