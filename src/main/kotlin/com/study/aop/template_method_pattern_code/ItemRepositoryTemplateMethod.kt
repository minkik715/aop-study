package com.study.aop.template_method_pattern_code

import com.study.aop.item.Item
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.template_method_pattern.LogHelperTemplateMethod
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

@Repository
class ItemRepositoryTemplateMethod(
    private val logService: LogServiceDirtyCode
) {

    val storage = hashMapOf<String, Item>()

    fun findItems(): List<Item> {
        return object : LogHelperTemplateMethod<List<Item>>(logService) {
            override fun call(): List<Item> {
                return storage.values.toList()
            }
        }.execute("ItemRepositoryTemplateMethod/findItems")
    }

    fun saveItem(item: Item): Item {
        return object : LogHelperTemplateMethod<Item>(logService) {
            override fun call(): Item {
                storage[item.id] = item
                return storage[item.id] ?: throw IllegalArgumentException("생성 버그")
            }
        }.execute("ItemRepositoryTemplateMethod/saveItem")
    }
}