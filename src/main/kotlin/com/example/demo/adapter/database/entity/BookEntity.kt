package com.example.demo.adapter.database.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class BookEntity (
    @Id var id: String,
    var title: String,
    var year: Int,
    var publisher: String,
    var author: String,
    var creationDate: LocalDateTime = LocalDateTime.now()
)