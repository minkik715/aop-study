package com.study.aop.jdk_dynamic_proxy.proxy

import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.data.Trace
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogInvocationHandler(
    private val target : Any,
    private val logService: LogServiceDirtyCode
) : InvocationHandler {


    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any?>?): Any {

        val className = method?.declaringClass?.name ?: ""
        val methodName = method?.name ?: ""
        if(methodName.contains("toString")){
            return target.toString();
        }
        var trace: Trace? = null

        return runCatching {
            trace = logService.begin(className + methodName)
            val methodArgs = args ?: emptyArray()
            method?.invoke(target, *methodArgs) ?: throw IllegalArgumentException("mehtod null")
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!, it)
            throw it
        }.getOrThrow()
    }
}