package com.github.llh4github.kinoapi.model.inner

import com.jihulab.llh4gitlab.kinoapi.model.BaseModel
import com.jihulab.llh4gitlab.kinoapi.model.BaseModelInput
import org.babyfish.jimmer.Input
import org.babyfish.jimmer.kt.new
import org.babyfish.jimmer.sql.*
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

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

data class OrganizationInput(
    var name: String? = null,
    var pId: Int? = null,
    var parent: OrganizationInput? = null,
    val children: MutableList<OrganizationInput> = mutableListOf(),
) : BaseModelInput(), Input<Organization> {
    override fun toEntity(): Organization {
        val model = CONVERTER.toModel(this)
        val by = new(Organization::class).by(model) {
            this@OrganizationInput.pId?.let {
                if (this@OrganizationInput.parent == null)
                    this.pId = it
            }
        }
        return by
    }

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }

    @Mapper
    internal interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toModel(input: OrganizationInput): Organization
    }
}
