package com.jihulab.llh4gitlab.kinoapi.service.inner.impl

import com.jihulab.llh4gitlab.kinoapi.dto.IdDto
import com.jihulab.llh4gitlab.kinoapi.dto.PageDto
import com.jihulab.llh4gitlab.kinoapi.dto.convert.DtoConvert
import com.jihulab.llh4gitlab.kinoapi.dto.inner.InsideUrlAddDto
import com.jihulab.llh4gitlab.kinoapi.dto.inner.InsideUrlQueryDto
import com.jihulab.llh4gitlab.kinoapi.dto.inner.InsideUrlUpdateDto
import com.jihulab.llh4gitlab.kinoapi.model.inner.*
import com.jihulab.llh4gitlab.kinoapi.repository.inner.InsideUrlRepository
import com.jihulab.llh4gitlab.kinoapi.service.inner.InsideUrlService
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
        val input = DtoConvert.insideUrl.toDbInput(dto)
        insideUrlRepository.save(input)
        return true
    }

    @Transactional
    override fun deleteById(list: IdDto) {
        insideUrlRepository.deleteByIds(list.ids)
    }

    @Transactional
    override fun updateByDto(dto: InsideUrlUpdateDto): Boolean {
        val input = DtoConvert.insideUrl.toDbInput(dto)
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
        return insideUrlRepository
            .pager(page.toPageRequest())
            .execute(condition)
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