package com.study.aop.advisor

import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.proxy.decoreator_pattern_code.ItemControllerRealSubject
import com.study.aop.proxy.decoreator_pattern_code.ItemRepositoryRealSubject
import com.study.aop.proxy.decoreator_pattern_code.ItemServiceRealSubject
import com.study.aop.proxy.decoreator_pattern_code.`interface`.ItemController
import com.study.aop.proxy.decoreator_pattern_code.`interface`.ItemRepository
import com.study.aop.proxy.decoreator_pattern_code.`interface`.ItemService
import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanAdvisorConfiguration {

    @Bean
    fun itemController(logService: LogServiceDirtyCode): ItemController {
        val proxyFactory = ProxyFactory(ItemControllerRealSubject(itemService(logService)))
        proxyFactory.addAdvisor(getAdvisor(logService))
        return proxyFactory.getProxy() as ItemController
    }


    @Bean
    fun itemService(logService: LogServiceDirtyCode): ItemService {
        val proxyFactory = ProxyFactory(ItemServiceRealSubject(itemRepository(logService)))
        proxyFactory.addAdvisor(getAdvisor(logService))
        return proxyFactory.getProxy() as ItemService

    }

    @Bean
    fun itemRepository(logService: LogServiceDirtyCode): ItemRepository {
        val proxyFactory = ProxyFactory(ItemRepositoryRealSubject())
        proxyFactory.addAdvisor(getAdvisor(logService))
        return proxyFactory.getProxy() as ItemRepository
    }

    private fun getAdvisor(logService: LogServiceDirtyCode): Advisor {
        val pointCut = NameMatchMethodPointcut()
        pointCut.setMappedNames("creat*", "get*", "save*", "find*")
        return DefaultPointcutAdvisor(pointCut, LogAdvice(logService))
    }
}