package com.study.aop.advisor

import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.data.Trace
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation

class LogAdvice(
    private val logService: LogServiceDirtyCode
) : MethodInterceptor{

    override fun invoke(invocation: MethodInvocation): Any? {
        val method = invocation.method
        val className = method.declaringClass.name
        val methodName = method.name
        var trace: Trace? = null

        return runCatching {
            trace = logService.begin(className + methodName)
            invocation.proceed()
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!, it)
            throw it
        }.getOrThrow()
    }
}