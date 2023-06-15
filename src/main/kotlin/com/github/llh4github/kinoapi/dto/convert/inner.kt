package com.github.llh4github.kinoapi.dto.convert

import com.github.llh4github.kinoapi.dto.inner.*
import com.github.llh4github.kinoapi.model.inner.InsideUrlInput
import com.github.llh4github.kinoapi.model.inner.MenuFront
import com.github.llh4github.kinoapi.model.inner.MenuFrontInput
import com.github.llh4github.kinoapi.model.inner.OrganizationInput
import org.mapstruct.Mapper


@Mapper
interface InsideUrlConvert {
    fun toDbInput(dto: InsideUrlAddDto): InsideUrlInput
    fun toDbInput(dto: InsideUrlUpdateDto): InsideUrlInput
}

@Mapper
interface OrganizationConvert {
    fun toDbInput(dto: OrganizationAddDto): OrganizationInput
}

@Mapper
interface MenuFrontConvert {

    fun toModel(input: MenuFrontInput): MenuFront
    fun toModel(input: MenuAddDto): MenuFront
    fun toModel(input: MenuUpdateDto): MenuFront
}