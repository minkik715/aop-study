package com.study.aop.item

import org.springframework.stereotype.Repository

@Repository
class ItemRepositoryBasic {

    val storage = hashMapOf<String, Item>()

    fun findItems(): List<Item> {
        return storage.values.toList()

    }

    fun saveItem(item: Item): Item {
        storage[item.id] = item
        return storage[item.id] ?: throw IllegalArgumentException("생성 버그")
    }
}