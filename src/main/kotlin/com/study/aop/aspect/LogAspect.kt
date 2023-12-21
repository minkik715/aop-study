package com.study.aop.aspect

import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.data.Trace
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class LogAspect(
    private val logService: LogServiceDirtyCode
) {

    @Around("execution(* com.study.aop.item.*.*(..))")
    fun logAspect(joinPoint: ProceedingJoinPoint): Any {
        var trace: Trace? = null

        return runCatching {
            trace = logService.begin(joinPoint.signature.toShortString())
            joinPoint.proceed()
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!, it)
            throw it
        }.getOrThrow()
    }



}