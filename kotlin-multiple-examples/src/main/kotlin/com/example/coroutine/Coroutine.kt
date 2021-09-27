package com.example.coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() {
    runBlocking {
        launch {
            printA()
        }
        println("MAIN")
    }

    runBlocking {
        execute()
        println("OVER")
    }

    println("\n\nJOB TIME")
    runBlocking {
        val job = launch { execute() }
        println("BEFORE JOB")
        job.join()
        println("AFTER JOB")
    }

    println("\n\nREPEATING ROUTINE")
    runBlocking {
        repeat(20) {
            launch { printNumber(it) }
        }
    }
}

suspend fun printNumber(int: Int) {
    val nextLong = Random.nextLong(1000, 2000)
    delay(nextLong)
    println("$int ($nextLong)")
}

suspend fun printA() {
    delay(1000L)
    println("A")
}

suspend fun execute() = coroutineScope {
    launch { executeFirst() }
    launch { executeBetween() }
    executeLast()
}

suspend fun executeFirst() {
    delay(2000L)
    println("LAST PRINT")
}

suspend fun executeBetween() {
    delay(1000L)
    println("MIDDLE PRINT")
}

suspend fun executeLast() {
    delay(500L)
    println("FIRST PRINT")
}