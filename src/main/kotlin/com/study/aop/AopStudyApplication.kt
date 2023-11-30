package com.study.aop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class AopStudyApplication

fun main(args: Array<String>) {
    runApplication<AopStudyApplication>(*args)
}
