package com.study.aop.logger.strategy_pattern

import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.data.Trace
import com.study.aop.logger.template_call_back_pattern.LogCallCallBack

class LogHelperStrategy<T>(
    private val logService: LogServiceDirtyCode,
    private val logCallStrategy: LogCallStrategy<T>,
) {


    fun execute(message: String): T {
        var trace: Trace? = null
        return runCatching {
            trace = logService.begin(message)
            logCallStrategy.call()
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!)
            throw it
        }.getOrThrow()
    }
}