package com.study.aop.jdk_dynamic_proxy.interfaces

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO

interface ItemServiceJdkProxy {
    fun getItems(): List<Item>

    fun createItem(item: ItemDTO): Item
}