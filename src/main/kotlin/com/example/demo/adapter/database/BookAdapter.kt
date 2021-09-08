package com.example.demo.adapter.database

import com.example.demo.adapter.database.entity.BookEntity
import com.example.demo.adapter.database.mapper.toOutput
import com.example.demo.adapter.database.repository.BookRepository
import com.example.demo.core.port.`in`.GetBookByIdPortIn
import com.example.demo.core.port.`in`.SaveBookPortIn
import com.example.demo.core.port.out.GetAllBooksPortOut
import com.example.demo.core.port.out.GetBookByIdPortOut
import com.example.demo.core.port.out.SaveBookPortOut
import mu.KLogging
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class SaveBookAdapter(private val bookRepository: BookRepository) : SaveBookPortOut {
    companion object: KLogging()
    override fun execute(book: SaveBookPortIn.Input): String {
        val bookEntity = BookEntity(
            UUID.randomUUID().toString(),
            book.title,
            book.year,
            book.publisher,
            book.author
        )
        logger.info("Saving book: $bookEntity")
        return bookRepository.save(bookEntity).id
    }
}

@Component
class GetBookAdapter(private val bookRepository: BookRepository) : GetBookByIdPortOut {
    override fun execute(id: String): Optional<GetBookByIdPortIn.Output> =
        bookRepository.findById(id).map { it.toOutput() }

    fun alternativeWay(id: String): GetBookByIdPortIn.Output? {
        return bookRepository.findByIdOrNull(id)
            ?.let { it.toOutput() }
    }
}

@Component
class GetAllBooksAdapter(private val bookRepository: BookRepository) : GetAllBooksPortOut {
    override fun execute(): List<GetBookByIdPortIn.Output> =
        bookRepository.findAll().map(BookEntity::toOutput);
}