package io.github.llh4github.kinoapi.model

import com.fasterxml.jackson.annotation.JsonFormat
import io.github.llh4github.kinoapi.model.auth.User
import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.sql.JoinColumn
import org.babyfish.jimmer.sql.ManyToOne
import org.babyfish.jimmer.sql.MappedSuperclass
import java.time.LocalDateTime

/**
 *
 * Created At 2023/12/1 20:46
 * @author llh
 */
@MappedSuperclass
interface BaseModel {

    @get:Schema(title = "创建时间")
    @get:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdTime: LocalDateTime

    @get:Schema(title = "更新时间")
    @get:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedTime: LocalDateTime

    @ManyToOne
    @JoinColumn(name = "created_by")
    @get:Schema(title = "创建者")
    val createdBy: User?

    @ManyToOne
    @JoinColumn(name = "updated_by")
    @get:Schema(title = "更新者")
    val updatedBy: User?
}