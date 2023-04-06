package com.jihulab.llh4gitlab.kinoapi.service.auth.impl

import com.jihulab.llh4gitlab.kinoapi.dto.auth.PermissionAddDto
import com.jihulab.llh4gitlab.kinoapi.dto.convert.DtoConvert
import com.jihulab.llh4gitlab.kinoapi.model.auth.Permission
import com.jihulab.llh4gitlab.kinoapi.model.auth.code
import com.jihulab.llh4gitlab.kinoapi.model.auth.id
import com.jihulab.llh4gitlab.kinoapi.model.auth.roles
import com.jihulab.llh4gitlab.kinoapi.repository.auth.PermissionRepository
import com.jihulab.llh4gitlab.kinoapi.service.auth.PermissionService
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.valueIn
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

    override fun idsInDb(idInput: List<Int>): List<Int> {
        if (idInput.isEmpty()) return emptyList()
        return permissionRepository.sql.createQuery(Permission::class) {
            where(table.id valueIn idInput)
            select(table.id)
        }.execute()
    }

    override fun codeList(roleIds: List<Int>): List<String> {
        if (roleIds.isEmpty()) return emptyList()
        return permissionRepository.sql.createQuery(Permission::class) {
            where(table.asTableEx().roles.id valueIn roleIds)
            select(table.code)
        }.execute()
    }

    override fun codeExist(code: String): Boolean {
        return permissionRepository.sql.createQuery(Permission::class) {
            where(table.code eq code)
            select(table.id)
        }.count() > 0
    }
}