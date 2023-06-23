package com.github.llh4github.kinoapi.repository

import com.github.llh4github.kinoapi.model.inner.helper.MenuFrontInput
import com.github.llh4github.kinoapi.repository.inner.MenuFrontRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

/**
 *
 * Created At 2023/5/23 15:06
 * @author llh
 */
@ActiveProfiles("dev")
@SpringBootTest
class MenuFrontRepositoryTest {

    @Autowired
    private lateinit var repository: MenuFrontRepository

    @Test
    fun `test query tree`() {
        val tree = repository.findTreeByRootId(2)
        println(tree)
    }

    @Test
    fun `test query tree list`() {
        val list = repository.findTreeList()
        println(list)
    }

    @Test
    fun testModify() {
        val input = MenuFrontInput(
            name = "test",
            router = "/test/test4",
            parentId = 1,
        )
        input.id = 11
        input.createdTime = LocalDateTime.now()
        input.updatedTime = LocalDateTime.now()
        repository.update(input)

    }

    @Test
    fun `test find son id`() {
        val list = repository.findSonIds(3)
        println(list)
    }
}