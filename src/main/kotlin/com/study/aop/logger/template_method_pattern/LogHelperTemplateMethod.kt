package com.study.aop.logger.template_method_pattern

import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.data.Trace

abstract class LogHelperTemplateMethod<T>(
    private val logService: LogServiceDirtyCode
) {

    protected abstract fun call(): T

    fun execute(message: String): T {
        var trace: Trace? = null

        return runCatching {
            trace = logService.begin(message)
            call()
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!)
            throw it
        }.getOrThrow()
    }
}