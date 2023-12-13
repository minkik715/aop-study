package com.study.aop.jdk_dynamic_proxy.impl

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.jdk_dynamic_proxy.interfaces.ItemControllerJdkProxy
import com.study.aop.jdk_dynamic_proxy.interfaces.ItemServiceJdkProxy
import org.springframework.web.bind.annotation.*

class ItemControllerJdkProxyImpl(
    private val itemService: ItemServiceJdkProxy
) : ItemControllerJdkProxy {

    override fun getItems(): List<Item>{
        return itemService.getItems()
    }


    override fun createItem(@RequestBody item: ItemDTO): Item{
        return itemService.createItem(item)
    }
}