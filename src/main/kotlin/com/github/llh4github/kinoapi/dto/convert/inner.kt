package com.github.llh4github.kinoapi.dto.convert

import com.jihulab.llh4gitlab.kinoapi.dto.inner.InsideUrlAddDto
import com.jihulab.llh4gitlab.kinoapi.dto.inner.InsideUrlUpdateDto
import com.jihulab.llh4gitlab.kinoapi.dto.inner.OrganizationAddDto
import com.jihulab.llh4gitlab.kinoapi.model.inner.InsideUrlInput
import com.jihulab.llh4gitlab.kinoapi.model.inner.OrganizationInput
import org.mapstruct.Mapper


@Mapper
interface InsideUrlConvert {
    fun toDbInput(dto: InsideUrlAddDto): InsideUrlInput
    fun toDbInput(dto: InsideUrlUpdateDto): InsideUrlInput
}

@Mapper
interface OrganizationConvert{
    fun toDbInput(dto: OrganizationAddDto): OrganizationInput
}