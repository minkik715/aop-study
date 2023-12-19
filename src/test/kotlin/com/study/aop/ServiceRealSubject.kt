package com.study.aop

class ServiceRealSubject: Service {
    override fun doSomething() {
        println("나는 실제 타겟 객체로 열심히 일을 한다.")
    }
}