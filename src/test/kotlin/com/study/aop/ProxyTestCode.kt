package com.study.aop

import org.junit.jupiter.api.Test

class ProxyTestCode {

    companion object {
        val serviceClient = ServiceProxy(ServiceRealSubject())
    }

    @Test
    fun serviceTest(){
        serviceClient.doSomething()
    }
}