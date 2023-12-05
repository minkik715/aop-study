package com.study.aop.logger.template_call_back_pattern

import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.data.Trace
import org.springframework.stereotype.Component

@Component
class LogHelperCallback(
    private val logService: LogServiceDirtyCode,
) {


    fun <T> execute(message: String, logCallCallback: LogCallCallBack<T>): T {
        var trace: Trace? = null
        return runCatching {
            trace = logService.begin(message)
            logCallCallback.call()
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!)
            throw it
        }.getOrThrow()
    }
}