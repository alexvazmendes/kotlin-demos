package com.example.demo.core.usecase

import com.example.demo.core.port.`in`.GetAllBooksPortIn
import com.example.demo.core.port.`in`.GetBookByIdPortIn
import com.example.demo.core.port.out.GetAllBooksPortOut
import org.springframework.stereotype.Service

@Service
class GetAllBooksUseCase(private val getAllBooksPortOut: GetAllBooksPortOut) : GetAllBooksPortIn {
    override fun execute(): List<GetBookByIdPortIn.Output> {
        return getAllBooksPortOut.execute();
    }
}