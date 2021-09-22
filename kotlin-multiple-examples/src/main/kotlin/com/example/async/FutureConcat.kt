package com.example.async

import java.util.concurrent.CompletableFuture

class FutureConcat

fun main() {
    doSomethingAsyncConcat()
        .thenAcceptBoth(CompletableFuture.supplyAsync{
            return456()
        }) { param1, param2 ->
            val paramRes = param1.plus(param2)
            println("Final Res: $paramRes")
        }.whenComplete { _, _ ->
            println("All over")
        }.get()

    println("\n\n\nNEW TEST")
    val get = doSomethingAsyncConcat()
        .thenCompose { str ->
            inputMoreParams(str)
        }
        .get()
    println("GET $get")
}

fun doSomethingAsyncConcat(): CompletableFuture<String> {
    val supplyAsync = CompletableFuture.supplyAsync {
        println("Something async")
        Thread.sleep(2000)
        println("Something async ended")
        "123"
    }

    return supplyAsync
}

fun return456(): String {
    println("456 async")
    Thread.sleep(500)
    println("456 ended")
    return "456"
}

fun inputMoreParams(str: String): CompletableFuture<String>? {
    val supplyAsync = CompletableFuture.supplyAsync {
        println("Input async")
        Thread.sleep(2000)
        println("Input async ended")
        "Hello ".plus(str)
    }

    return supplyAsync
}