package com.study.aop.cglib

import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.data.Trace
import org.springframework.cglib.proxy.MethodInterceptor
import org.springframework.cglib.proxy.MethodProxy
import java.lang.reflect.Method

class LogMethodInterceptor(
    private val logService: LogServiceDirtyCode,
    private val target: Any
): MethodInterceptor {
    override fun intercept(obj: Any?, method: Method?, args: Array<out Any>?, methodProxy: MethodProxy?): Any {
        val className = method?.declaringClass?.name ?: ""
        val methodName = method?.name ?: ""
        if(methodName.contains("toString")){
            return target.toString();
        }
        var trace: Trace? = null

        return runCatching {
            trace = logService.begin(className + methodName)
            val methodArgs = args ?: emptyArray()
            methodProxy?.invoke(target, methodArgs) ?: throw IllegalArgumentException("mehtod null")
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!, it)
            throw it
        }.getOrThrow()


    }
}