package com.example.demo.adapter.database

import com.example.demo.adapter.database.entity.BookEntity
import com.example.demo.adapter.database.repository.BookRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
internal class GetBookAdapterTest @Autowired constructor(
    private val repository: BookRepository
) {

    @BeforeEach
    fun setup() = repository.deleteAll()

    @Test
    fun `Quando buscar id nao presente no banco - Entao retorna Optional vazio`() {
        val getBookAdapter = GetBookAdapter(repository)
        val optional = getBookAdapter.execute("1234-1234")
        assertTrue(optional.isEmpty)
    }

    @Test
    fun `Quando buscar dado no banco com sucesso - Entao retorna Entidade`() {
        val getBookAdapter = GetBookAdapter(repository)
        repository.save(BookEntity("1234", "titulo", 1999,
            "editora", "autor"))

        val optional = getBookAdapter.execute("1234")
        assertTrue(optional.isPresent)
        assertEquals(1999, optional.get().year)
    }
}