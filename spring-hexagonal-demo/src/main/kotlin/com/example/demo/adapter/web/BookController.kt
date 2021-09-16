package com.example.demo.adapter.web

import com.example.demo.adapter.web.dto.BookRequestDTO
import com.example.demo.adapter.web.dto.BookResponseDTO
import com.example.demo.adapter.web.dto.GetBookResponseDTO
import com.example.demo.adapter.web.mapper.toInput
import com.example.demo.adapter.web.mapper.toResponseDto
import com.example.demo.core.port.`in`.GetAllBooksPortIn
import com.example.demo.core.port.`in`.GetBookByIdPortIn
import com.example.demo.core.port.`in`.SaveBookPortIn
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController (private val saveBookPortIn: SaveBookPortIn,
                      private val getBookByIdPortIn: GetBookByIdPortIn,
                      private val getAllBooksPortIn: GetAllBooksPortIn) {
    companion object: KLogging()

    @PostMapping
    fun save(@RequestBody bookRequestDTO: BookRequestDTO): ResponseEntity<BookResponseDTO> {
        logger.info("BookDTO received for saving: $bookRequestDTO")
        val output = saveBookPortIn.execute(bookRequestDTO.toInput())
        logger.info("Book saved. Id: ${output.id}")
        return ResponseEntity.status(HttpStatus.CREATED).body(output.toResponseDto(bookRequestDTO.title))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: String): ResponseEntity<GetBookResponseDTO> {
        logger.info("Getting book by id: $id")
        val output = getBookByIdPortIn.execute(id)
        logger.info("Book Found: $output")
        return ResponseEntity.ok(output.toResponseDto())
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<GetBookResponseDTO>> {
        logger.info("Getting All Books.")
        val output = getAllBooksPortIn.execute()
        return output.takeIf { it.isNotEmpty() }
            ?.map { it.toResponseDto() }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.noContent().build()
    }
}