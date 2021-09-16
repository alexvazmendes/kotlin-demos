package com.example.demo.adapter.database

import com.example.demo.adapter.database.repository.BookRepository
import com.example.demo.core.port.`in`.SaveBookPortIn
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.util.*

@DataJpaTest
internal class SaveBookAdapterTest @Autowired constructor(
     private val repository: BookRepository
) {

    @Test
    fun `Quando salvar livro - Entao retorna o Id e o livro eh salvo`() {
        val input = SaveBookPortIn.Input("titulo", "autor",
            1998, "editora123")

        val saveBookAdapter = SaveBookAdapter(repository)

        val id = saveBookAdapter.execute(input)
        assertNotNull(id)
        assertDoesNotThrow { UUID.fromString(id) }

        val findById = repository.findById(id)
        assertTrue(findById.isPresent)
        assertEquals("titulo", findById.get().title)
    }
}