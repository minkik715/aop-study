package com.study.aop.aspect.pointcut

import org.aspectj.lang.annotation.Pointcut

class Pointcuts {

    @Pointcut("execution(* com.study.aop.item.*.*(..))")
    fun allItemBasic(){}

    @Pointcut("execution(* *..*Service*.*(..))")
    fun allService(){}

    @Pointcut("allService() && allItemBasic()")
    fun itemService(){}
}