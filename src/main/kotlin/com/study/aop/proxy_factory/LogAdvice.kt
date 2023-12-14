package com.study.aop.proxy_factory

import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.data.Trace
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation

class LogAdvice(val logService: LogServiceDirtyCode) : MethodInterceptor {
    override fun invoke(invocation: MethodInvocation): Any? {
        val method = invocation.method
        val className = method.declaringClass?.name ?: ""
        val methodName = method.name ?: ""

        var trace: Trace? = null
        if(methodName.contains("getClass")){
            return null
        }
        return runCatching {
            trace = logService.begin(className + methodName)
            invocation.proceed() ?: throw IllegalArgumentException("mehtod null")
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!, it)
            throw it
        }.getOrThrow()
    }
}