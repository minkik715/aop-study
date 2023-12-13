package com.study.aop.jdk_dynamic_proxy.impl

import com.study.aop.item.Item
import com.study.aop.jdk_dynamic_proxy.interfaces.ItemRepositoryJdkProxy

class ItemRepositoryJdkProxyImpl : ItemRepositoryJdkProxy {


    val storage = hashMapOf<String, Item>()

    override fun findItems(): List<Item> {
        return storage.values.toList()

    }

    override fun saveItem(item: Item): Item {
        storage[item.id] = item
        return storage[item.id] ?: throw IllegalArgumentException("생성 버그")
    }
}