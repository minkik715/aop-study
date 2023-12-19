package com.study.aop.bean_post_processor

import com.study.aop.advisor.LogAdvice
import com.study.aop.logger.LogServiceDirtyCode
import org.springframework.aop.Advisor
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LogPostProcessorConfiguration {

    @Bean
    fun logBeanPostProcessor(logService: LogServiceDirtyCode): LogBeanPostProcessor {
        return LogBeanPostProcessor("com.study.aop.item", getAdvisor(logService))
    }

    fun getAdvisor(logService: LogServiceDirtyCode): Advisor {
        val logAdvice = LogAdvice(logService)
        val pointCut = NameMatchMethodPointcut()
        pointCut.setMappedNames("creat*", "get*", "save*", "find*")
        return DefaultPointcutAdvisor(logAdvice)
    }

}