package com.example.demo.adapter.web

import com.example.demo.core.domain.Book
import com.example.demo.core.port.`in`.SaveBookPortIn
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class CarController (private val saveBookPortIn: SaveBookPortIn) {

    @PostMapping
    fun getCar(): String = saveBookPortIn.execute(book = Book("",
    "", 1998, ""))
}