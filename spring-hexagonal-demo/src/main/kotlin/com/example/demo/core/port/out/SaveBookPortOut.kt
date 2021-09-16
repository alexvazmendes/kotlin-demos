package com.example.demo.core.port.out

import com.example.demo.core.port.`in`.SaveBookPortIn

interface SaveBookPortOut {
    fun execute(book: SaveBookPortIn.Input): String
}