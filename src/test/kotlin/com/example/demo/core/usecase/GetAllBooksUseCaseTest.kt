package com.example.demo.core.usecase

import com.example.demo.core.port.`in`.GetBookByIdPortIn
import com.example.demo.core.port.out.GetAllBooksPortOut
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class GetAllBooksUseCaseTest {
    @Test
    fun `Quando busca lista de livros - Entao retorna mesma lista`() {
        val mock = mock<GetAllBooksPortOut> {
            on { execute() } doReturn
                    listOf(GetBookByIdPortIn.Output("1234",
                    "titulo", "autor", 2020, "editora1",
                    "2021-01-01"))
        }

        val useCase = GetAllBooksUseCase(mock)
        val list = useCase.execute()
        assertEquals("1234", list[0].id)
        verify(mock).execute()
    }
}