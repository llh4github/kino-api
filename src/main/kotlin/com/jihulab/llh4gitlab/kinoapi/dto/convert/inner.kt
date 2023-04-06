package com.jihulab.llh4gitlab.kinoapi.dto.convert

import com.jihulab.llh4gitlab.kinoapi.dto.inner.InsideUrlAddDto
import com.jihulab.llh4gitlab.kinoapi.model.inner.InsideUrlInput
import org.mapstruct.Mapper


@Mapper
interface InsideUrlConvert {
    fun toDbInput(dto: InsideUrlAddDto): InsideUrlInput
}