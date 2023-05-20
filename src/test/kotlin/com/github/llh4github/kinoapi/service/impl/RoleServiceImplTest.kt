package com.github.llh4github.kinoapi.service.impl

import com.jihulab.llh4gitlab.kinoapi.service.auth.impl.RoleServiceImpl
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 *
 *
 * Created At 2023/3/30 15:37
 * @author llh
 */
@SpringBootTest
class RoleServiceImplTest {

    @Autowired
    private lateinit var service: RoleServiceImpl

    @Test
    fun `test join query`() {
        service.roleCodes(1)
    }
}