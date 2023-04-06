package com.jihulab.llh4gitlab.kinoapi.service.impl

import com.jihulab.llh4gitlab.kinoapi.service.auth.impl.UserServiceImpl
import com.jihulab.llh4gitlab.kinoapi.util.checkPwd
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 *
 *
 * Created At 2023/4/2 14:55
 * @author llh
 */
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private lateinit var service: UserServiceImpl

    @Test
    fun `test pwd hash`() {
//        service.addByDto(UserAddDto(username = "Jerry", password = "Asd098"))
        val saved = service.findByUsername("Jerry")
        saved?.let {
            assertTrue(checkPwd("Asd098", it.password))
        }
    }
}