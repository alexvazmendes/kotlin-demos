package com.example.demo.core.port.out

import com.example.demo.core.port.`in`.GetBookByIdPortIn

interface GetAllBooksPortOut {
    fun execute() : List<GetBookByIdPortIn.Output>
}