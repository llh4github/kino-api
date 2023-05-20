package com.github.llh4github.kinoapi.service.inner.impl

import com.github.llh4github.kinoapi.dto.convert.DtoConvert
import com.github.llh4github.kinoapi.dto.inner.OrganizationAddDto
import com.github.llh4github.kinoapi.model.inner.Organization
import com.github.llh4github.kinoapi.repository.inner.OrganizationRepository
import com.github.llh4github.kinoapi.service.inner.OrganizationService
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