package com.study.aop.proxy.decoreator_pattern_code

import com.study.aop.item.Item
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.strategy_pattern.LogHelperStrategy
import com.study.aop.proxy.decoreator_pattern_code.`interface`.ItemRepository
import org.springframework.stereotype.Repository

class ItemRepositoryRealSubject(
) : ItemRepository {

    val storage = hashMapOf<String, Item>()

    override fun findItems(): List<Item> {
        return storage.values.toList()
    }

    override fun saveItem(item: Item): Item {
        storage[item.id] = item
        return storage[item.id] ?: throw IllegalArgumentException("생성 버그")
    }
}