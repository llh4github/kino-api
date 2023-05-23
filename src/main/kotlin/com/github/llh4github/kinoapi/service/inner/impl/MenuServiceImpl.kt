package com.github.llh4github.kinoapi.service.inner.impl

import com.github.llh4github.kinoapi.dto.convert.InnerConvert
import com.github.llh4github.kinoapi.dto.inner.MenuAddDto
import com.github.llh4github.kinoapi.model.inner.MenuFront
import com.github.llh4github.kinoapi.repository.inner.MenuFrontRepository
import com.github.llh4github.kinoapi.service.inner.MenuService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MenuServiceImpl(
    private val repository: MenuFrontRepository
) : MenuService {
    @Transactional
    override fun addByDto(dto: MenuAddDto): Boolean {
        val model = InnerConvert.menu.toModel(dto)
        repository.save(model)
        return true
    }

    override fun treeList(parentId: Int?): List<MenuFront> {
        return repository.findTreeList(parentId)
    }

    override fun tree(id: Int): MenuFront? {
        return repository.findTreeByRootId(id)
    }
}