package com.study.aop.strategy_pattern_code

import com.study.aop.item.Item
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.strategy_pattern.LogHelperStrategy
import com.study.aop.logger.template_method_pattern.LogHelperTemplateMethod
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

@Repository
@Qualifier("ItemRepositoryTemplateMethod")
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