package com.jihulab.llh4gitlab.kinoapi.service.inner.impl

import com.jihulab.llh4gitlab.kinoapi.dto.convert.DtoConvert
import com.jihulab.llh4gitlab.kinoapi.dto.inner.OrganizationAddDto
import com.jihulab.llh4gitlab.kinoapi.model.inner.Organization
import com.jihulab.llh4gitlab.kinoapi.repository.inner.OrganizationRepository
import com.jihulab.llh4gitlab.kinoapi.service.inner.OrganizationService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrganizationServiceImpl(
    private val organizationRepository: OrganizationRepository
) : OrganizationService {

    @Transactional
    override fun addByDto(dto: OrganizationAddDto): Boolean {
        val model = DtoConvert.organization.toDbInput(dto)
        organizationRepository.insert(model)
        return true
    }

    override fun findTree(id: Int): Organization? {
        return organizationRepository.findTree(id)
    }
}