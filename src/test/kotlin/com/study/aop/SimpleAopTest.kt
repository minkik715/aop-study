package com.study.aop

import com.study.aop.aspect.SimpleAspect
import com.study.aop.item.ItemDTO
import com.study.aop.item.ItemRepositoryBasic
import com.study.aop.item.ItemServiceBasic
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(SimpleAspect::class)
class SimpleAopTest {

    @Autowired
    lateinit var itemServiceBasic: ItemServiceBasic

    @Autowired
    lateinit var itmeRepositoryBasic: ItemRepositoryBasic

    @Test
    fun simpleAopTest(){
        Assertions.assertThat(AopUtils.isAopProxy(itemServiceBasic)).isTrue()
        itemServiceBasic.createItem(ItemDTO("바보"))
    }

}