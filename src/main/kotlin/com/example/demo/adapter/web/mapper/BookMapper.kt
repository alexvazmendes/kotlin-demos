package com.example.demo.adapter.web.mapper

import com.example.demo.adapter.web.dto.BookRequestDTO
import com.example.demo.adapter.web.dto.BookResponseDTO
import com.example.demo.core.port.`in`.SaveBookPortIn

fun BookRequestDTO.toInput() = SaveBookPortIn.Input(
    title = title,
    author = author,
    year = year,
    publisher = publisher
)

fun SaveBookPortIn.Output.toResponseDto(title: String) = BookResponseDTO(
    id = id,
    title = title
)