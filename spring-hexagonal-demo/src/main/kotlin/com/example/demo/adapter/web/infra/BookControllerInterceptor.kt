package com.example.demo.adapter.web.infra

import mu.KLogging
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class BookControllerInterceptor : HandlerInterceptor {
    companion object: KLogging()

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val transactionId = request.getHeader("Transaction-Id")
            ?: UUID.randomUUID().toString()
        MDC.put("transactionId", transactionId)
        logger.info("Request received!")

        return true
    }
}