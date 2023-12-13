package com.study.aop.logger

import com.study.aop.logger.data.Trace
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.lang.StringBuilder

@Service
class LogServiceDirtyCode() {

    private val traceThreadLocal = ThreadLocal<Trace.TraceId>()


    fun begin(domain: String): Trace {
        val trace = traceThreadLocal.get()?.let {
            Trace(domain, it.getNext())
        }?: Trace(domain)
        traceThreadLocal.set(trace.traceId)
        println("${getSpace(trace.traceId.depth, Trace.BEGIN_MARK)} $domain ${trace.traceId.id} start at ${System.currentTimeMillis()}")
        return trace
    }

    fun finish(trace: Trace) {
        complete(trace)
    }

    fun execption(trace: Trace, e: Throwable) {
        complete(trace, e)
    }

    private fun complete(trace: Trace, e: Throwable? = null) {
        val id = trace.traceId.id
        val depth = trace.traceId.depth
        val domain = trace.domain
        val startedAt = trace.startedAt
        if (e == null) {
            println("${getSpace(depth, Trace.FINISH_MARK)} $domain $id is taken ${System.currentTimeMillis() - startedAt}")
        } else {
            println("${getSpace(depth, Trace.EXCEPTION_MARK)} $domain $id throw exception ${e.message}")
        }

        release()
    }

    private fun release() {
        traceThreadLocal.get()?.let {
            if(it.isFirst()){
                traceThreadLocal.remove()
            }else{
                traceThreadLocal.set(it.getPrevious())
            }
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