package com.example.demo.core.port.out

import com.example.demo.core.port.`in`.GetBookByIdPortIn

interface GetBookByIdPortOut {
    fun execute(id: String): GetBookByIdPortIn.Output
}