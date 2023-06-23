package com.github.llh4github.kinoapi.model

import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.sql.GeneratedValue
import org.babyfish.jimmer.sql.GenerationType
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.MappedSuperclass
import java.time.LocalDateTime


/**
 *
 * Created At 2023/3/28 20:53
 * @author llh
 */
@MappedSuperclass
interface BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Schema(title = "数据ID", example = "1")
    val id: Int

    @get:Schema(title = "创建时间")
    val createdTime: LocalDateTime

    @get:Schema(title = "更新时间")
    val updatedTime: LocalDateTime

    @get:Schema(title = "更新者ID")
    val updatedBy: Int?

    @get:Schema(title = "创建者ID")
    val createdBy: Int?
}
