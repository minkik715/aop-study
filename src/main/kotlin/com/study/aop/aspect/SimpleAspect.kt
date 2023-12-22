package com.study.aop.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory

@Aspect
class SimpleAspect {

    val log = LoggerFactory.getLogger(SimpleAspect::class.java)


    @Around("com.study.aop.aspect.pointcut.Pointcuts.allItemBasic()")
    fun simpleLogAdvisor(joinPoint: ProceedingJoinPoint) : Any{
        log.info(joinPoint.signature.toShortString())
        return joinPoint.proceed()
    }


    @Around("com.study.aop.aspect.pointcut.Pointcuts.itemService()")
    fun simpleTransaction(joinPoint: ProceedingJoinPoint): Any{
        val signature = joinPoint.signature.toShortString()
        return runCatching {
            log.info("$signature 트랜잭션 시작")
            joinPoint.proceed()
        }.onSuccess {
            log.info("$signature 트랜잭션 성공")
            log.info("$signature 트랜잭션 완료")
        }.onFailure {
            log.info("$signature 트랜잭션 실패")
            log.info("$signature 트랜잭션 완료")
            throw it
        }.getOrThrow()
    }

}