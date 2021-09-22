package com.example.async

import java.util.concurrent.CompletableFuture

class Future

fun main () {
    val doSomethingAsync = doSomethingAsync()
    println("MAIN")
    
    doSomethingAsync.whenComplete { t, u ->
        println("Return $t")
        println("Error? $u")
    }
    doSomethingAsync.get()
}

fun doSomethingAsync(): CompletableFuture<String> {
    val supplyAsync = CompletableFuture.supplyAsync {
        println("Something async")
        Thread.sleep(2000)
        println("Something async ended")
        "123"
    }

    return supplyAsync;
}


