package com.github.llh4github.kinoapi.service.auth.impl

import com.jihulab.llh4gitlab.kinoapi.dto.IdDto
import com.jihulab.llh4gitlab.kinoapi.dto.PageDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.PermissionAddDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.PermissionQueryDto
import com.jihulab.llh4gitlab.kinoapi.dto.auth.PermissionUpdateDto
import com.jihulab.llh4gitlab.kinoapi.dto.convert.DtoConvert
import com.jihulab.llh4gitlab.kinoapi.model.auth.*
import com.jihulab.llh4gitlab.kinoapi.repository.auth.PermissionRepository
import com.jihulab.llh4gitlab.kinoapi.service.auth.PermissionService
import org.babyfish.jimmer.sql.kt.ast.expression.*
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PermissionServiceImpl(
    private val permissionRepository: PermissionRepository
) : PermissionService {
    @Transactional
    override fun addByDto(dto: PermissionAddDto): Boolean {
        val input = DtoConvert.permission.toDbInput(dto)
        permissionRepository.insert(input)
        return true
    }

    override fun all(): List<Permission> {
        return permissionRepository.findAll()
    }

    @Transactional
    override fun updateByDto(dto: PermissionUpdateDto): Boolean {
        val input = DtoConvert.permission.toDbInput(dto)
        permissionRepository.update(input)
        return true
    }

    @Transactional
    override fun deleteByIds(ids: IdDto): Boolean {
        permissionRepository.deleteByIds(ids.ids)
        return true
    }

    override fun idsInDb(idInput: List<Int>): List<Int> {
        if (idInput.isEmpty()) return emptyList()
        return permissionRepository.sql.createQuery(Permission::class) {
            where(table.id valueIn idInput)
            select(table.id)
        }.execute()
    }

    override fun pageQuery(
        page: PageDto, query: PermissionQueryDto?
    ): Page<Permission> {
        val condition = permissionRepository.sql.createQuery(Permission::class) {
            query?.code?.takeIf { it.isNotEmpty() }?.let {
                where(table.code like it)
            }
            query?.name?.takeIf { it.isNotEmpty() }?.let {
                where(table.name like it)
            }
            orderBy(table.updatedTime.desc())
            select(table)
        }
        return permissionRepository
            .pager(page.toPageRequest())
            .execute(condition)
    }

    override fun codeList(roleIds: List<Int>): List<String> {
        if (roleIds.isEmpty()) return emptyList()
        return permissionRepository.sql.createQuery(Permission::class) {
            where(table.asTableEx().roles.id valueIn roleIds)
            select(table.code)
        }.execute()
    }

    override fun codeExist(code: String, notId: Int?): Boolean {
        return permissionRepository.sql.createQuery(Permission::class) {
            where(table.code eq code)
            notId?.let {
                where(table.id ne notId)
            }
            select(table.id)
        }.count() > 0
    }
}