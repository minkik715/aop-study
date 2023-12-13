package com.study.aop.jdk_dynamic_proxy.config

import com.study.aop.jdk_dynamic_proxy.impl.ItemControllerJdkProxyImpl
import com.study.aop.jdk_dynamic_proxy.impl.ItemRepositoryJdkProxyImpl
import com.study.aop.jdk_dynamic_proxy.impl.ItemServiceJdkProxyImpl
import com.study.aop.jdk_dynamic_proxy.proxy.LogInvocationHandler
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.jdk_dynamic_proxy.interfaces.ItemControllerJdkProxy
import com.study.aop.jdk_dynamic_proxy.interfaces.ItemRepositoryJdkProxy
import com.study.aop.jdk_dynamic_proxy.interfaces.ItemServiceJdkProxy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import java.lang.reflect.Proxy

@Configuration
@ComponentScan(basePackages = ["com.study.aop.logger"])
class ItemJdkProxyBeanConfiguration {
    @Bean
    fun itemControllerJdkProxy(logService: LogServiceDirtyCode): ItemControllerJdkProxy {
        return Proxy.newProxyInstance(
            ItemControllerJdkProxy::class.java.classLoader,
            arrayOf(ItemControllerJdkProxy::class.java),
            LogInvocationHandler(ItemControllerJdkProxyImpl(itemServiceJdkProxy(logService)), logService)
        ) as ItemControllerJdkProxy
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