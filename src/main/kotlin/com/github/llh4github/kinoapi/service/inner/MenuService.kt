package com.github.llh4github.kinoapi.service.inner

import com.github.llh4github.kinoapi.dto.inner.MenuAddDto
import com.github.llh4github.kinoapi.dto.inner.MenuUpdateDto
import com.github.llh4github.kinoapi.model.inner.MenuFront

/**
 *
 * Created At 2023/5/23 17:31
 * @author llh
 */
interface MenuService {

    fun addByDto(dto: MenuAddDto): Boolean
    fun updateByDto(dto:MenuUpdateDto): Boolean

    fun treeList(parentId: Int? = null): List<MenuFront>

    fun tree(id: Int): MenuFront?
}