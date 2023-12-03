package io.github.llh4github.kinoapi.service.auth.impl

import io.github.llh4github.kinoapi.dao.auth.UserDao
import io.github.llh4github.kinoapi.model.auth.User
import io.github.llh4github.kinoapi.service.auth.UserService
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val dao: UserDao
) : UserService {
    companion object : Logging

    override fun findById(id: Int): User? {
        return dao.findNullable(id)
    }
}