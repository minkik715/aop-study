package com.study.aop.template_callback_pattern_code

import com.study.aop.item.Item
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.template_call_back_pattern.LogHelperCallback
import org.springframework.stereotype.Repository

@Repository
class ItemRepositoryTemplateCallback(
    private val logService: LogServiceDirtyCode
) {

    val storage = hashMapOf<String, Item>()

    fun findItems(): List<Item> {
        return LogHelperCallback<List<Item>>(logService).execute("ItemRepositoryTemplateMethod/findItems") {
            storage.values.toList()
        }
    }

    fun saveItem(item: Item): Item {
        return LogHelperCallback<Item>(logService).execute("ItemRepositoryTemplateMethod/saveItem") {
            storage[item.id] = item
            storage[item.id] ?: throw IllegalArgumentException("생성 버그")
        }
    }
}