package com.example.demo.adapter.web.mapper

import com.example.demo.adapter.web.dto.BookRequestDTO
import com.example.demo.adapter.web.dto.BookResponseDTO
import com.example.demo.adapter.web.dto.GetBookResponseDTO
import com.example.demo.core.port.`in`.GetBookByIdPortIn
import com.example.demo.core.port.`in`.SaveBookPortIn

fun BookRequestDTO.toInput() = SaveBookPortIn.Input(title,
    author, year, publisher
)

fun SaveBookPortIn.Output.toResponseDto(title: String) = BookResponseDTO(
    id, title
)

fun GetBookByIdPortIn.Output.toResponseDto() = GetBookResponseDTO(id,
    title, author, year, publisher, createdAt
)