package com.example.demo.adapter.web.infra

import com.example.demo.adapter.web.dto.ErrorResponseDTO
import com.example.demo.core.exception.BookNotFoundException
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class BookExceptionHandler {
    companion object: KLogging()

    @ExceptionHandler(BookNotFoundException::class)
    fun handleBookNotFound(e: BookNotFoundException): ResponseEntity<ErrorResponseDTO> {
        logger.error("Error finding book.", e)
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorResponseDTO("Book not found"))
    }
}