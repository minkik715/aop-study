package com.study.aop.jdk_dynamic_proxy.`interface`

import com.study.aop.item.Item

interface ItemRepositoryJdkProxy {

    fun findItems(): List<Item>

    fun saveItem(item: Item): Item
}