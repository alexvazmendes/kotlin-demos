package com.example.demo.core.usecase

import com.example.demo.core.port.`in`.SaveBookPortIn
import com.example.demo.core.port.out.SaveBookPortOut
import com.example.demo.core.usecase.mapper.toOutput
import org.springframework.stereotype.Service

@Service
class SaveBookUseCase(private val saveBookPortOut: SaveBookPortOut) : SaveBookPortIn {
    override fun execute(book: SaveBookPortIn.Input): SaveBookPortIn.Output =
        saveBookPortOut.execute(book).toOutput()
}