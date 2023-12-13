package com.study.aop.jdk_dynamic_proxy.impl

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.jdk_dynamic_proxy.interfaces.ItemRepositoryJdkProxy
import com.study.aop.jdk_dynamic_proxy.interfaces.ItemServiceJdkProxy

class ItemServiceJdkProxyImpl(
    private val itemRepository: ItemRepositoryJdkProxy
) : ItemServiceJdkProxy {
    override fun getItems(): List<Item> {
        return itemRepository.findItems()
    }

    override fun createItem(item: ItemDTO): Item {
        return itemRepository.saveItem(item.toItem())
    }

}