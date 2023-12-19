package com.study.aop.bean_post_processor

import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.beans.factory.config.BeanPostProcessor

class LogBeanPostProcessor(
    private val packageName : String,
    private val advisor: Advisor
) : BeanPostProcessor {



    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {

        val beanPackage = bean.javaClass.packageName

        if(beanPackage != packageName){
            return super.postProcessAfterInitialization(bean, beanName)
        }

        val proxyFactory = ProxyFactory(bean)
        proxyFactory.addAdvisor(advisor)
        val proxy = proxyFactory.getProxy()

        return super.postProcessAfterInitialization(proxy, beanName)
    }
}