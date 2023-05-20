package com.github.llh4github.kinoapi.util

import cn.dev33.satoken.secure.BCrypt

/**
 * 将输入[raw]进行hash
 */
fun hashPwd(raw: String) = BCrypt.hashpw(raw)

/**
 * 输入的[raw]与数据库的[pwdInDB]进行对比
 */
fun checkPwd(raw: String, pwdInDB: String) = BCrypt.checkpw(raw, pwdInDB)
