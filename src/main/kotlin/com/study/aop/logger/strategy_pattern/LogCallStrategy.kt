package com.study.aop.logger.strategy_pattern

fun interface LogCallStrategy<T> {

    fun call() : T
}