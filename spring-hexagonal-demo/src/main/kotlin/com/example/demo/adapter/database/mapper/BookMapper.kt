package com.example.demo.adapter.database.mapper

import com.example.demo.adapter.database.entity.BookEntity
import com.example.demo.core.port.`in`.GetBookByIdPortIn
import java.time.format.DateTimeFormatter

fun BookEntity.toOutput() = GetBookByIdPortIn.Output(id, title, author,
year, publisher, creationDate.format(DateTimeFormatter.ISO_DATE_TIME))