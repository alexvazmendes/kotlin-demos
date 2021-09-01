package com.example.demo.adapter.web

import com.example.demo.adapter.web.dto.BookRequestDTO
import com.example.demo.adapter.web.dto.BookResponseDTO
import com.example.demo.adapter.web.mapper.toInput
import com.example.demo.adapter.web.mapper.toResponseDto
import com.example.demo.core.domain.Book
import com.example.demo.core.port.`in`.SaveBookPortIn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController (private val saveBookPortIn: SaveBookPortIn) {

    @PostMapping
    fun save(@RequestBody bookRequestDTO: BookRequestDTO): ResponseEntity<BookResponseDTO> {
        var output = saveBookPortIn.execute(bookRequestDTO.toInput())
        return ResponseEntity.ok(output.toResponseDto(bookRequestDTO.title))
    }
}