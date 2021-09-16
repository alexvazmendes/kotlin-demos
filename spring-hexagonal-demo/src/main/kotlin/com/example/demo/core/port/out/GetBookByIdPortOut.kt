package com.example.demo.core.port.out

import com.example.demo.core.port.`in`.GetBookByIdPortIn
import java.util.*

interface GetBookByIdPortOut {
    fun execute(id: String): Optional<GetBookByIdPortIn.Output>
}