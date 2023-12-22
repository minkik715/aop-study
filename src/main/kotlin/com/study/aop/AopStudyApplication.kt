package com.study.aop

import com.study.aop.logger.LogServiceDirtyCode
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication(scanBasePackages = ["com.study.aop.item"])
class AopStudyApplication {
    @Bean
    fun logService(): LogServiceDirtyCode {
        return LogServiceDirtyCode()
    }
}

fun main(args: Array<String>) {


    runApplication<AopStudyApplication>(*args)
}
