package com.example.demo.core.usecase

import com.example.demo.core.exception.BookNotFoundException
import com.example.demo.core.port.`in`.GetBookByIdPortIn
import com.example.demo.core.port.out.GetBookByIdPortOut
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class GetBookByIdUseCaseTest {
    @Mock
    lateinit var getBookByIdPortOut: GetBookByIdPortOut
    @InjectMocks
    lateinit var getBookByIdUseCase: GetBookByIdUseCase

    @Test
    fun `Quando busca com sucesso - Entao retorna objeto`() {
        val output = GetBookByIdPortIn.Output(
            "123", "titulo", "autor", 2010,
            "editora", "2020-01-08T09:00:00"
        )
        doReturn(Optional.of(output)).`when`(getBookByIdPortOut)
            .execute("123")

        val book = getBookByIdUseCase.execute("123")
        assertEquals("123", book.id)
        assertEquals(2010, book.year)
    }

    @Test
    fun `Quando busca retorna vazio - Entao lanca BookNotFoundException`() {
        doReturn(Optional.empty<GetBookByIdPortIn.Output>()).`when`(getBookByIdPortOut)
            .execute("123")

        val exception = assertThrows<BookNotFoundException> {
            getBookByIdUseCase.execute("123")
        }
        assertEquals("Book not found", exception.message)
    }
}