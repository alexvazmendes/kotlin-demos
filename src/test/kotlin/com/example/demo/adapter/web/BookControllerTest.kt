package com.example.demo.adapter.web

import com.example.demo.adapter.web.dto.BookRequestDTO
import com.example.demo.adapter.web.dto.ErrorResponseDTO
import com.example.demo.adapter.web.mapper.toResponseDto
import com.example.demo.core.exception.BookNotFoundException
import com.example.demo.core.port.`in`.GetAllBooksPortIn
import com.example.demo.core.port.`in`.GetBookByIdPortIn
import com.example.demo.core.port.`in`.SaveBookPortIn
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@AutoConfigureMockMvc
@WebMvcTest(controllers = [BookController::class])
internal class BookControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var saveBookPortIn: SaveBookPortIn
    @MockBean
    lateinit var getBookByIdPortIn: GetBookByIdPortIn
    @MockBean
    lateinit var getAllBooksPortIn: GetAllBooksPortIn

    private lateinit var bookRequestDTO: BookRequestDTO

    @BeforeEach
    fun setup() {
        bookRequestDTO = BookRequestDTO("titulo",
            "autor", 1999, "editora1")
    }

    @Test
    fun `Quando salvar livro com sucesso - Entao retorna 201`() {
        val output = SaveBookPortIn.Output("1234-4321")
        doReturn(output).`when`(saveBookPortIn)
            .execute(any())

        this.mockMvc.perform(post("/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(bookRequestDTO)))

            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().`is`(201))
            .andExpect(MockMvcResultMatchers.content().json(
                objectMapper.writeValueAsString(output.toResponseDto(bookRequestDTO.title))
            ))
    }

    @Test
    fun `Quando buscar por id com sucesso - Entao Retorna livro`() {
        val output = GetBookByIdPortIn.Output(
            "123-321", "titulo2",
            "autor", 2000,
            "editora123", "2001-03-03T08:00:00"
        )
        doReturn(output)
            .`when`(getBookByIdPortIn).execute("123-321")

        mockMvc.perform(get("/books/123-321"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(
                objectMapper.writeValueAsString(output.toResponseDto())
            ))
    }

    @Test
    fun `Quando buscar por id e nao encontrar - Entao Retorna 404`() {
        doAnswer { throw BookNotFoundException("") }
            .`when`(getBookByIdPortIn).execute("123-321")

        mockMvc.perform(get("/books/123-321"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().json(
                objectMapper.writeValueAsString(ErrorResponseDTO("Book not found"))
            ))
    }

    @Test
    fun `Quando busca todos os livros com lista vazia - Entao retorna 204`() {
        doReturn(Collections.emptyList<GetBookByIdPortIn.Output>())
            .`when`(getAllBooksPortIn)
            .execute()

        mockMvc.perform(get("/books"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isNoContent)
    }

    @Test
    fun `Quando busca todos os livros com sucesso - Entao retorna os livros`() {
        val output1 = GetBookByIdPortIn.Output("321-321",
        "titulo1", "autor1", 2010, "editora1",
        "2001-03-04T08:00:00")
        val output2 = GetBookByIdPortIn.Output(
            "123-321", "titulo2",
            "autor2", 2000,
            "editora123", "2001-03-03T08:00:00"
        )
        val arrayListOf = arrayListOf(output1, output2)
        doReturn(arrayListOf).`when`(getAllBooksPortIn)
            .execute()

        mockMvc.perform(get("/books"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(
                objectMapper.writeValueAsString(arrayListOf.stream()
                    .map { it.toResponseDto() })
            ))
    }
}