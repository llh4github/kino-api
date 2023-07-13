package com.github.llh4github.kinoapi.service

import com.github.llh4github.kinoapi.dto.PageDto
import org.babyfish.jimmer.spring.repository.fetchPage
import org.babyfish.jimmer.sql.kt.ast.query.KConfigurableRootQuery
import org.springframework.data.domain.Page


fun <E> KConfigurableRootQuery<*, E>.pageQuery(param: PageDto): Page<E> {
    val page = if (param.page < 1) param.page else param.page - 1
    return this.fetchPage(page, param.size)
}