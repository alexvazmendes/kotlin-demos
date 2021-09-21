package com.example.callback

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.example.callback.execute as executeAlias

class Callback
suspend fun main() {
    println("Hello Main 1")
    callbackFunction { str -> lowerCase(str)}
    println("Hello Main 2")

    executeAlias()
    println("END")
}

suspend fun execute() {
    println("Hello execute")
    val suspendCoroutine = suspendCoroutine<String> { continuation ->
        callbackFunction { str ->
            lowerCase(str)
            continuation.resume("BBB")
        }
    }
    println(suspendCoroutine)
}

fun callbackFunction(callback: (String) -> Unit) {
    println("Hello callbackFunction")
    Thread.sleep(2000L)

    callback("AAAA")
}

fun lowerCase(testString: String): String {
    println("Hello lowerCase")
    val lowercase = testString.lowercase();
    println(lowercase)
    return lowercase
}