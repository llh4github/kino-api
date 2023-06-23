package com.github.llh4github.kinoapi.model.inner

import com.github.llh4github.kinoapi.model.BaseModel
import org.babyfish.jimmer.sql.*

/**
 *
 * Created At 2023/4/6 15:03
 * @author llh
 */
@Entity
@Table(name = "inner_organization")
interface Organization : BaseModel {

    @Key
    val name: String

    @ManyToOne
    val parent: Organization?

    @OneToMany(mappedBy = "parent")
    val children: List<Organization>

    @IdView("parent")
    val pId: Int?
}
