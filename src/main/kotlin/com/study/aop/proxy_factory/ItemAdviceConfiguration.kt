package com.study.aop.proxy_factory

import com.study.aop.jdk_dynamic_proxy.impl.ItemControllerJdkProxyImpl
import com.study.aop.jdk_dynamic_proxy.impl.ItemServiceJdkProxyImpl
import com.study.aop.jdk_dynamic_proxy.interfaces.ItemControllerJdkProxy
import com.study.aop.jdk_dynamic_proxy.interfaces.ItemRepositoryJdkProxy
import com.study.aop.jdk_dynamic_proxy.interfaces.ItemServiceJdkProxy
import com.study.aop.logger.LogServiceDirtyCode
import org.aopalliance.aop.Advice
import org.springframework.aop.framework.ProxyFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.study.aop.logger"])
class ItemAdviceConfiguration {

    @Bean
    fun itemControllerProxyFactory(logService: LogServiceDirtyCode): ItemControllerJdkProxy {
        val proxyFactory = ProxyFactory(ItemControllerJdkProxyImpl(itemServiceProxyFactory(logService)))
        proxyFactory.addAdvice(LogAdvice(logService))
        return proxyFactory.proxy as ItemControllerJdkProxy
    }

    @Bean
    fun itemServiceProxyFactory(logService: LogServiceDirtyCode): ItemServiceJdkProxy {
        val proxyFactory = ProxyFactory(ItemServiceJdkProxyImpl(itemRepositoryProxyFactory(logService)))
        proxyFactory.addAdvice(LogAdvice(logService))
        return proxyFactory.proxy as ItemServiceJdkProxy
    }

    @Bean
    fun itemRepositoryProxyFactory(logService: LogServiceDirtyCode): ItemRepositoryJdkProxy {
        val proxyFactory = ProxyFactory(ItemRepositoryJdkProxy::class.java)
        proxyFactory.addAdvice(LogAdvice(logService))
        return proxyFactory.proxy as ItemRepositoryJdkProxy
    }

}