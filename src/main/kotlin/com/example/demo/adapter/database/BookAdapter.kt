package com.example.demo.adapter.database

import com.example.demo.adapter.database.entity.BookEntity
import com.example.demo.adapter.database.repository.BookRepository
import com.example.demo.core.port.`in`.SaveBookPortIn
import com.example.demo.core.port.out.SaveBookPortOut
import java.util.*

class SaveBookAdapter(private val bookRepository: BookRepository) : SaveBookPortOut{
    override fun execute(book: SaveBookPortIn.Input): String {
        var bookEntity = BookEntity(
            UUID.randomUUID().toString(),
            book.title,
            book.year,
            book.publisher,
            book.author
        )
        return bookRepository.save(bookEntity).id
    }
}