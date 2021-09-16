package com.example.demo.adapter.database

import com.example.demo.adapter.database.entity.BookEntity
import com.example.demo.adapter.database.repository.BookRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
internal class GetAllBooksAdapterTest @Autowired constructor(
    private val repository: BookRepository
) {
    @BeforeEach
    fun setup() = repository.deleteAll()

    @Test
    fun `Quando busca lista com elementos - Entao retorna todos elementos`() {
        repository.save(
            BookEntity("1234", "titulo1", 1999,
            "editora1", "autor1"))
        repository.save(BookEntity("1235", "titulo2", 1991,
            "editora2", "autor2"))

        val getAllBooksAdapter = GetAllBooksAdapter(repository)

        val list = getAllBooksAdapter.execute()
        assertEquals(2, list.size)
    }

    @Test
    fun `Quando busca lista sem elementos - Entao retorna lista vazia`() {
        val getAllBooksAdapter = GetAllBooksAdapter(repository)

        val list = getAllBooksAdapter.execute()
        assertTrue(list.isEmpty())
    }
}
