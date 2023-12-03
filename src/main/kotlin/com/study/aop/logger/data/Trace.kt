package com.study.aop.logger.data

import java.util.*

data class Trace(
    val domain: String,
    val traceId: TraceId = TraceId(),
    val startedAt: Long = System.currentTimeMillis(),
) {
    companion object {
        val BEGIN_MARK = "-->"
        val FINISH_MARK = "<--"
        val EXCEPTION_MARK = "<X-"
    }

    data class TraceId(
        val id: String = UUID.randomUUID().toString().substring(0, 8),
        val depth: Int = 0
    ) {
        fun getNext(): TraceId {
            return TraceId(this.id, this.depth + 1)
        }

        fun getPrevious(): TraceId {
            return TraceId(this.id, this.depth + 1)
        }

        fun isFirst(): Boolean {
            return (this.depth == 1)
        }
    }
}

