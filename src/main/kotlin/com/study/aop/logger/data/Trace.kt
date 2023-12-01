package com.study.aop.logger.data

import java.lang.StringBuilder
import java.util.*

data class Trace(
    val domain: String,
    val traceId: TraceId = TraceId(),
    val startedAt: Long = System.currentTimeMillis(),
) {
    companion object{
        val traceIdLoclThread = ThreadLocal<TraceId>()
    }

    data class TraceId(
        val id: String = UUID.randomUUID().toString().substring(0, 8),
        val depth: Int = 0
    )

    val begin = "-->"
    val finish = "<--"
    val exception = "<X-"


    fun begin(domain: String): Trace {
        val trace = traceIdLoclThread.get()?.let {

        }?: Trace(domain)
        println("${getSpace(trace.traceId.depth, begin)} $domain ${trace.traceId.id} start at ${System.currentTimeMillis()}")
        return trace
    }

    fun finish(trace: Trace) {
        complete(trace)
    }

    fun execption(trace: Trace) {
        complete(trace)
    }

    private fun complete(trace: Trace, e: Exception? = null) {
        val id = trace.traceId.id
        val depth = trace.traceId.depth
        val domain = trace.domain
        val startedAt = trace.startedAt
        if (e == null) {
            println("${getSpace(depth, finish)} $domain $id is taken ${System.currentTimeMillis() - startedAt}}")
        } else {
            println("${getSpace(depth, exception)} $domain $id throw exception ${e.message}}")
        }
    }

    private fun getSpace(depth: Int, type: String): String {
        val sb = StringBuilder()
        for (i in (0..depth)) {
            if (i == depth) {
                sb.append(type)
            } else {
                sb.append("  |")
            }
        }
        return sb.toString()
    }

}

