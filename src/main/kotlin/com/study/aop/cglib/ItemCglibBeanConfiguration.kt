package com.study.aop.cglib

import com.study.aop.jdk_dynamic_proxy.impl.ItemControllerJdkProxyImpl
import com.study.aop.jdk_dynamic_proxy.impl.ItemRepositoryJdkProxyImpl
import com.study.aop.jdk_dynamic_proxy.impl.ItemServiceJdkProxyImpl
import com.study.aop.jdk_dynamic_proxy.proxy.LogInvocationHandler
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.jdk_dynamic_proxy.interfaces.ItemControllerJdkProxy
import com.study.aop.jdk_dynamic_proxy.interfaces.ItemRepositoryJdkProxy
import com.study.aop.jdk_dynamic_proxy.interfaces.ItemServiceJdkProxy
import com.study.aop.proxy.decoreator_pattern_code.ItemRepositoryRealSubject
import org.springframework.cglib.proxy.Enhancer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import java.lang.reflect.Proxy

//@Configuration
//@ComponentScan(basePackages = ["com.study.aop.logger"])
class ItemCglibBeanConfiguration {
    @Bean
    fun itemRepositoryProxy(logService: LogServiceDirtyCode): ItemRepositoryRealSubject {
        return Enhancer.create(
            ItemRepositoryRealSubject::class.java,
            LogMethodInterceptor(logService, ItemRepositoryRealSubject())
        ) as ItemRepositoryRealSubject
    }

    @Bean
    fun itemServiceJdkProxy(logService: LogServiceDirtyCode): ItemServiceJdkProxy {
        return Proxy.newProxyInstance(
            ItemServiceJdkProxy::class.java.classLoader,
            arrayOf(ItemServiceJdkProxy::class.java),
            LogInvocationHandler(ItemServiceJdkProxyImpl(itemRepositoryJdkProxy(logService)), logService)
        ) as ItemServiceJdkProxy
    }

    @Bean
    fun itemRepositoryJdkProxy(logService: LogServiceDirtyCode): ItemRepositoryJdkProxy {
        return Proxy.newProxyInstance(
            ItemRepositoryJdkProxy::class.java.classLoader,
            arrayOf(ItemRepositoryJdkProxy::class.java),
            LogInvocationHandler(ItemRepositoryJdkProxyImpl(), logService)
        ) as ItemRepositoryJdkProxy
    }
}