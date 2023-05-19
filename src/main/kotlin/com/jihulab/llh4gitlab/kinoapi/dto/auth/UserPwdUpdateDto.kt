package com.jihulab.llh4gitlab.kinoapi.dto.auth

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jihulab.llh4gitlab.kinoapi.dto.BaseUpdateDto
import com.jihulab.llh4gitlab.kinoapi.util.hashPwd
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.Length

/**
 *
 *
 * Created At 2023/5/18 10:38
 * @author llh
 */
@Schema(title = "更新密码类")
data class UserPwdUpdateDto(
    @get:NotEmpty
    @get:Length(min = 8)
    @field:Schema(title = "新密码")
    val password: String
) : BaseUpdateDto() {

    @get:JsonIgnore
    @field:Schema(hidden = true)
    val hashedPwd: String = hashPwd(password)
}
