package com.github.llh4github.kinoapi.service.inner.impl

import com.github.llh4github.kinoapi.dto.IdsDto
import com.github.llh4github.kinoapi.dto.PageDto
import com.github.llh4github.kinoapi.dto.inner.InsideUrlAddDto
import com.github.llh4github.kinoapi.dto.inner.InsideUrlQueryDto
import com.github.llh4github.kinoapi.dto.inner.InsideUrlUpdateDto
import com.github.llh4github.kinoapi.dto.inner.toJimmerEntity
import com.github.llh4github.kinoapi.model.inner.*
import com.github.llh4github.kinoapi.repository.inner.InsideUrlRepository
import com.github.llh4github.kinoapi.service.inner.InsideUrlService
import org.babyfish.jimmer.spring.repository.fetchPage
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.like
import org.babyfish.jimmer.sql.kt.fetcher.newFetcher
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InsideUrlServiceImpl(
    private val insideUrlRepository: InsideUrlRepository
) : InsideUrlService {
    @Transactional
    override fun addByDto(dto: InsideUrlAddDto): Boolean {
        val input = dto.toJimmerEntity()
        insideUrlRepository.save(input)
        return true
    }

    @Transactional
    override fun deleteById(list: IdsDto) {
        insideUrlRepository.deleteByIds(list.ids)
    }

    @Transactional
    override fun updateByDto(dto: InsideUrlUpdateDto): Boolean {
        val input = dto.toJimmerEntity()
        insideUrlRepository.update(input)
        return true
    }

    override fun pageQuery(page: PageDto, query: InsideUrlQueryDto): Page<InsideUrl> {
        val condition = insideUrlRepository.sql.createQuery(InsideUrl::class) {
            query.method?.let {
                where(table.method eq it)
            }
            query.url?.takeIf { it.isNotEmpty() }?.let {
                where(table.url like it)
            }
            query.remark?.takeIf { it.isNotEmpty() }?.let {
                where(table.remark like it)
            }
            select(table)
        }
        return condition.fetchPage(page.page, page.size)
    }

    override fun urlExist(url: String): Boolean {
        return insideUrlRepository.sql.createQuery(InsideUrl::class) {
            where(table.url eq url)
            select(table.id)
        }.count() > 0
    }

    override fun allUrlAndPermissionCode(): List<InsideUrl> {
        return insideUrlRepository.findAll(fetcher = newFetcher(InsideUrl::class).by {
            allScalarFields()
            permissions { code() }
        })
    }
}