package com.github.llh4github.kinoapi.service.auth.impl

import com.github.llh4github.kinoapi.dto.IdsDto
import com.github.llh4github.kinoapi.dto.PageDto
import com.github.llh4github.kinoapi.dto.auth.RoleAddDto
import com.github.llh4github.kinoapi.dto.auth.RoleQueryDto
import com.github.llh4github.kinoapi.dto.auth.RoleUpdateDto
import com.github.llh4github.kinoapi.dto.convert.DtoConvert
import com.github.llh4github.kinoapi.model.auth.*
import com.github.llh4github.kinoapi.repository.auth.RoleRepository
import com.github.llh4github.kinoapi.service.auth.RoleService
import org.apache.logging.log4j.kotlin.Logging
import org.babyfish.jimmer.sql.kt.ast.expression.*
import org.springframework.beans.BeanUtils
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RoleServiceImpl(
    private val repository: RoleRepository
) : RoleService, Logging {

    override fun allSimple(): List<Role> {
        return repository.findAll()
    }

    override fun detail(id: Int): Role? {
        return repository.findNullable(id, RoleFetcher.simpleFetch)
    }


    override fun existCode(code: String, notId: Int?): Boolean {
        return repository.sql.createQuery(Role::class) {
            where(table.code eq code)
            notId?.let {
                where(table.id ne it)
            }
            select(table.code)
        }.count() > 0
    }

    @Transactional
    override fun deleteByIds(ids: IdsDto) {
        repository.deleteByIds(ids.ids)
    }

    override fun pageQuery(page: PageDto, query: RoleQueryDto?): Page<Role> {
        val condition = repository.sql.createQuery(Role::class) {
            query?.code?.takeIf { it.isNotEmpty() }?.let {
                where(table.code like it)
            }
            query?.name?.takeIf { it.isNotEmpty() }?.let {
                where(table.name like it)
            }
            orderBy(table.updatedTime.desc())
            select(table)
        }
        return repository
            .pager(page.toPageRequest())
            .execute(condition)
    }

    @Transactional
    override fun addByDto(dto: RoleAddDto): Boolean {
        val model = DtoConvert.role.toDbInput(dto)
        BeanUtils.copyProperties(dto, RoleInput())
        repository.insert(model)
        return true
    }

    @Transactional
    override fun updateByDto(dto: RoleUpdateDto): Boolean {
        val model = DtoConvert.role.toDbInput(dto)
        repository.update(model)
        return true
    }

    override fun hasCode(code: String): Boolean {
        return repository.existsByCode(code)
    }

    override fun ids(userId: Int): List<Int> {
        return repository.sql.createQuery(Role::class) {
            where(table.asTableEx().users.id eq userId)
            select(table.id)
        }.execute()
    }

    override fun roleCodes(userId: Int): List<String> {
        return repository.findRoleCodeByUserId(userId)
    }

    override fun idsInDb(idInput: List<Int>): List<Int> {
        if (idInput.isEmpty()) return emptyList()
        return repository.sql.createQuery(Role::class) {
            where(table.id valueIn idInput)
            select(table.id)
        }.execute().distinct()
    }
}