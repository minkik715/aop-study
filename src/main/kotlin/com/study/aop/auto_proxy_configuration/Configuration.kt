package com.study.aop.auto_proxy_configuration

import com.study.aop.advisor.LogAdvice
import com.study.aop.logger.LogServiceDirtyCode
import org.springframework.aop.Advisor
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {

    @Bean
    fun logProxyAdvisor(logService: LogServiceDirtyCode): Advisor {
        return getAdvisor(logService)
    }

    private fun getAdvisor(logService: LogServiceDirtyCode): Advisor {
        val pointCut = AspectJExpressionPointcut()

        pointCut.expression = "execution(* com.study.aop.item.*.*(..))"
        return DefaultPointcutAdvisor(pointCut, LogAdvice(logService))
    }

}