package com.example.demo.adapter.database.repository

import com.example.demo.adapter.database.entity.BookEntity
import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<BookEntity, String> {
}