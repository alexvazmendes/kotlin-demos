package com.example.demo.adapter.database.mapper

import com.example.demo.adapter.database.entity.BookEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.LocalDateTime

internal class BookMapperKtTest {

    @Test
    fun `Quando Converte BookEntity Para Output - Entao Retorna Novo Objeto`() {
        val entity = BookEntity("1234",
            "Title1",
            1995,
            "Editora 123",
            "João",
            LocalDateTime.of(2010, 1, 2, 8, 12)
        )
        val output = entity.toOutput()

        assertAll("Verificando objeto de saída",
            { assertEquals("1234", output.id) },
            { assertEquals("Title1", output.title) },
            { assertEquals(1995, output.year) },
            { assertEquals("Editora 123", output.publisher) },
            { assertEquals("João", output.author) },
            { assertEquals("2010-01-02T08:12:00", output.createdAt) })
    }
}