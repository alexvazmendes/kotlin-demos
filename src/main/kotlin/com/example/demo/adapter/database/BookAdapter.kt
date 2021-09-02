package com.example.demo.adapter.database

import com.example.demo.adapter.database.entity.BookEntity
import com.example.demo.adapter.database.mapper.toOutput
import com.example.demo.adapter.database.repository.BookRepository
import com.example.demo.core.port.`in`.GetBookByIdPortIn
import com.example.demo.core.port.`in`.SaveBookPortIn
import com.example.demo.core.port.out.GetBookByIdPortOut
import com.example.demo.core.port.out.SaveBookPortOut
import org.springframework.stereotype.Component
import java.util.*

@Component
class SaveBookAdapter(private val bookRepository: BookRepository) : SaveBookPortOut {
    override fun execute(book: SaveBookPortIn.Input): String {
        val bookEntity = BookEntity(
            UUID.randomUUID().toString(),
            book.title,
            book.year,
            book.publisher,
            book.author
        )
        return bookRepository.save(bookEntity).id
    }
}

@Component
class GetBookAdapter(private val bookRepository: BookRepository) : GetBookByIdPortOut {
    override fun execute(id: String): GetBookByIdPortIn.Output =
        bookRepository.findById(id).get().toOutput()

}