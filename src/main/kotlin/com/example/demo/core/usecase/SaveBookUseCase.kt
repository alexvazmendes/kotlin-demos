package com.example.demo.core.usecase

import com.example.demo.core.domain.Book
import com.example.demo.core.port.`in`.SaveBookPortIn
import org.springframework.stereotype.Service
import java.util.*

@Service
class SaveBookUseCase : SaveBookPortIn{
    override fun execute(book: Book): String {
        return UUID.randomUUID().toString()
    }
}