package com.example.demo.core.usecase

import com.example.demo.core.exception.BookNotFoundException
import com.example.demo.core.port.`in`.GetBookByIdPortIn
import com.example.demo.core.port.out.GetBookByIdPortOut
import org.springframework.stereotype.Service

@Service
class GetBookByIdUseCase(private val getBookByIdPortOut: GetBookByIdPortOut) : GetBookByIdPortIn{
    override fun execute(id: String): GetBookByIdPortIn.Output =
        getBookByIdPortOut.execute(id)
            .orElseThrow{ BookNotFoundException("Book not found") }
}