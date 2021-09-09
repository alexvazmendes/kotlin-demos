package com.example.demo.core.usecase

import com.example.demo.core.port.`in`.SaveBookPortIn
import com.example.demo.core.port.out.SaveBookPortOut
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

internal class SaveBookUseCaseTest {
    val input = SaveBookPortIn.Input(
    "titulo", "autor", 2004, "editora"
    )

    @Test
    fun `Quando salvar com sucesso - Entao retorna id`() {
        val mock : SaveBookPortOut = mock()
        val saveBookUseCase = SaveBookUseCase(mock)

        whenever(mock.execute(any())).thenReturn("1234-1234")

        val out = saveBookUseCase.execute(
            input
        )

        assertEquals("1234-1234", out.id)
        verify(mock).execute(any())
    }

    @Test
    fun `Quando salvar com exceção - Entao a exceção é lançada`() {
        val mock : SaveBookPortOut = mock()
        val saveBookUseCase = SaveBookUseCase(mock)

        doThrow(NullPointerException()).`when`(mock)
            .execute(any())

        org.junit.jupiter.api.assertThrows<NullPointerException> {
            saveBookUseCase.execute(input)
        }
    }
}