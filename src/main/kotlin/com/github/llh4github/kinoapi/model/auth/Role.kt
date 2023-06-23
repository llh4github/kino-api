package com.github.llh4github.kinoapi.model.auth

import com.github.llh4github.kinoapi.model.BaseModel
import org.babyfish.jimmer.sql.*
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher


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

object RoleFetcher{
    val simpleFetch = newFetcher(Role::class).by {
        allScalarFields()
        permissions {
            code()
            name()
        }
    }
}
