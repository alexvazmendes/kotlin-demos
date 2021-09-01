package com.example.demo.core.usecase.mapper

import com.example.demo.core.port.`in`.SaveBookPortIn

fun String.toOutput() = SaveBookPortIn.Output(
    id = this
)