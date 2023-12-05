package com.study.aop.logger.template_call_back_pattern

fun interface LogCallCallBack<T> {

    fun call() : T
}