package com.example.demo.adapter.web

import com.example.demo.adapter.web.dto.BookRequestDTO
import com.example.demo.adapter.web.dto.BookResponseDTO
import com.example.demo.adapter.web.dto.GetBookResponseDTO
import com.example.demo.adapter.web.mapper.toInput
import com.example.demo.adapter.web.mapper.toResponseDto
import com.example.demo.core.port.`in`.GetBookByIdPortIn
import com.example.demo.core.port.`in`.SaveBookPortIn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/books")
class BookController (private val saveBookPortIn: SaveBookPortIn,
                      private val getBookByIdPortIn: GetBookByIdPortIn) {

    @PostMapping
    fun save(@RequestBody bookRequestDTO: BookRequestDTO): ResponseEntity<BookResponseDTO> {
        val output = saveBookPortIn.execute(bookRequestDTO.toInput())
        return ResponseEntity.ok(output.toResponseDto(bookRequestDTO.title))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: String): ResponseEntity<GetBookResponseDTO> {
        val output = getBookByIdPortIn.execute(id)
        return ResponseEntity.ok(output.toResponseDto())
    }
}