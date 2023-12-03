package io.github.llh4github.kinoapi.model

import io.github.llh4github.kinoapi.model.auth.User
import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.sql.JoinColumn
import org.babyfish.jimmer.sql.MappedSuperclass
import org.babyfish.jimmer.sql.OneToOne
import java.time.LocalDateTime

/**
 *
 * Created At 2023/12/1 20:46
 * @author llh
 */
@MappedSuperclass
interface BaseModel {

    @get:Schema(title = "创建时间")
    val createdTime: LocalDateTime

    @get:Schema(title = "更新时间")
    val updatedTime: LocalDateTime

    @OneToOne
    @JoinColumn(name = "created_by")
    @get:Schema(title = "创建者")
    val createdBy: User?

    @OneToOne
    @JoinColumn(name = "created_by")
    @get:Schema(title = "更新者")
    val updatedBy: User?
}