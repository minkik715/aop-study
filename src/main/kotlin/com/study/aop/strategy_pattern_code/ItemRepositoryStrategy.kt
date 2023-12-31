package com.study.aop.strategy_pattern_code

import com.study.aop.item.Item
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.strategy_pattern.LogHelperStrategy
import org.springframework.stereotype.Repository

@Repository
class ItemRepositoryStrategy(
    private val logService: LogServiceDirtyCode
) {

    val storage = hashMapOf<String, Item>()

    fun findItems(): List<Item> {
        return LogHelperStrategy(logService) {
            storage.values.toList()
        }.execute("ItemRepositoryTemplateMethod/findItems")
    }

    fun saveItem(item: Item): Item {
        return LogHelperStrategy(logService) {
            storage[item.id] = item
            storage[item.id] ?: throw IllegalArgumentException("생성 버그")
        }.execute("ItemRepositoryTemplateMethod/saveItem")
    }
}