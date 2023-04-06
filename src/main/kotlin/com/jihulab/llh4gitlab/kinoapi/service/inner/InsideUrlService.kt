package com.jihulab.llh4gitlab.kinoapi.service.inner

import com.jihulab.llh4gitlab.kinoapi.dto.PageDto
import com.jihulab.llh4gitlab.kinoapi.dto.inner.InsideUrlAddDto
import com.jihulab.llh4gitlab.kinoapi.dto.inner.InsideUrlQueryDto
import com.jihulab.llh4gitlab.kinoapi.model.inner.InsideUrl
import org.springframework.data.domain.Page

/**
 *
 * Created At 2023/4/4 17:01
 * @author llh
 */
interface InsideUrlService {
    fun addByDto(dto: InsideUrlAddDto): Boolean

    fun urlExist(url: String): Boolean
    fun pageQuery(page: PageDto, query: InsideUrlQueryDto): Page<InsideUrl>

    /**
     * 所有url及对应的权限编码
     *
     * [InsideUrl.permissions]中只有id和code值
     */
    fun allUrlAndPermissionCode(): List<InsideUrl>
}