package com.jihulab.llh4gitlab.kinoapi.dto.auth

/**
 * 用户信息类
 *
 * Created At 2023/5/7 9:42
 * @author llh
 */
data class UserInfoDto(
    val id: Int,
    val username: String,
    val avatar: String,
    val email: String,
    val roles: List<String>
)
