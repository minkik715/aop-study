package com.study.aop.proxy.decoreator_pattern_code.decorator

import com.study.aop.logger.LogServiceDirtyCode

abstract class LogDecorator {
    protected abstract val logService: LogServiceDirtyCode
}