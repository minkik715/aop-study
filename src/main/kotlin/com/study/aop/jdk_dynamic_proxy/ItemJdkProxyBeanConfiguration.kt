package com.study.aop.jdk_dynamic_proxy

import com.study.aop.jdk_dynamic_proxy.impl.ItemControllerJdkProxyImpl
import com.study.aop.jdk_dynamic_proxy.impl.ItemRepositoryJdkProxyImpl
import com.study.aop.jdk_dynamic_proxy.impl.ItemServiceJdkProxyImpl
import com.study.aop.jdk_dynamic_proxy.proxy.LogInvocationHandler
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.jdk_dynamic_proxy.`interface`.ItemControllerJdkProxy
import com.study.aop.jdk_dynamic_proxy.`interface`.ItemRepositoryJdkProxy
import com.study.aop.jdk_dynamic_proxy.`interface`.ItemServiceJdkProxy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.reflect.Proxy

@Configuration
class ItemJdkProxyBeanConfiguration{
    @Bean
    fun itemControllerJdkProxy(): ItemControllerJdkProxy {
        val logService = logService()
        return Proxy.newProxyInstance(
            ItemControllerJdkProxy::class.java.classLoader,
            arrayOf(ItemControllerJdkProxy::class.java),
            LogInvocationHandler(ItemControllerJdkProxyImpl(itemServiceJdkProxy()), logService)
        ) as ItemControllerJdkProxy
    }

    @Bean
    fun itemServiceJdkProxy(): ItemServiceJdkProxy {
        val logService = logService()
        return Proxy.newProxyInstance(
            ItemServiceJdkProxy::class.java.classLoader,
            arrayOf(ItemServiceJdkProxy::class.java),
            LogInvocationHandler(ItemServiceJdkProxyImpl(itemRepositoryJdkProxy()), logService)
        ) as ItemServiceJdkProxy
    }

    @Bean
    fun itemRepositoryJdkProxy(): ItemRepositoryJdkProxy {
        val logService = logService()
        return Proxy.newProxyInstance(
            ItemRepositoryJdkProxy::class.java.classLoader,
            arrayOf(ItemRepositoryJdkProxy::class.java),
            LogInvocationHandler(ItemRepositoryJdkProxyImpl(), logService)
        ) as ItemRepositoryJdkProxy
    }




    @Bean
    fun logService(): LogServiceDirtyCode{
        return LogServiceDirtyCode()
    }

}