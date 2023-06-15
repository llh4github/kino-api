package com.github.llh4github.kinoapi.service.inner

import com.github.llh4github.kinoapi.dto.IdsDto
import com.github.llh4github.kinoapi.dto.PageDto
import com.github.llh4github.kinoapi.dto.inner.InsideUrlAddDto
import com.github.llh4github.kinoapi.dto.inner.InsideUrlQueryDto
import com.github.llh4github.kinoapi.dto.inner.InsideUrlUpdateDto
import com.github.llh4github.kinoapi.model.inner.InsideUrl
import org.springframework.data.domain.Page

/**
 *
 * Created At 2023/4/4 17:01
 * @author llh
 */
interface InsideUrlService {
    fun addByDto(dto: InsideUrlAddDto): Boolean
    fun updateByDto(dto: InsideUrlUpdateDto): Boolean

    fun deleteById(list: IdsDto)
    fun urlExist(url: String): Boolean
    fun pageQuery(page: PageDto, query: InsideUrlQueryDto): Page<InsideUrl>

    /**
     * 所有url及对应的权限编码
     *
     * [InsideUrl.permissions]中只有id和code值
     */
    fun allUrlAndPermissionCode(): List<InsideUrl>
}