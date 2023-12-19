package com.study.aop

class ServiceProxy(
    private val target: ServiceRealSubject
): Service {
    override fun doSomething() {
        println("나는 프록시로 실제 객체 전에 열심히 일을 한다.")
        target.doSomething()
    }
}