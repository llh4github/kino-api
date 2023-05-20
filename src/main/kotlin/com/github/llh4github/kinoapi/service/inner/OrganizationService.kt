package com.github.llh4github.kinoapi.service.inner

import com.github.llh4github.kinoapi.dto.inner.OrganizationAddDto
import com.github.llh4github.kinoapi.model.inner.Organization

/**
 *
 * Created At 2023/4/6 19:16
 * @author llh
 */
interface OrganizationService {

    fun addByDto(dto: OrganizationAddDto): Boolean

    fun findTree(id: Int): Organization?
}