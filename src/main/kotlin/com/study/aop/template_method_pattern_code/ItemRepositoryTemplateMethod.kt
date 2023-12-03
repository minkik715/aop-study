package com.study.aop.thread_local_dirty_code

import com.study.aop.item.Item
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.data.Trace
import com.study.aop.logger.template_method_pattern.AbstractLogHelper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

@Repository
@Qualifier("ItemRepositoryTemplateMethod")
class ItemRepositoryTemplateMethod(
    private val logService: LogServiceDirtyCode
) {

    val storage = hashMapOf<String, Item>()

    fun findItems(): List<Item> {
        return object : AbstractLogHelper<List<Item>>(logService) {
            override fun call(): List<Item> {
                return storage.values.toList()
            }
        }.execute("ItemRepositoryTemplateMethod/findItems")
    }

    fun saveItem(item: Item): Item {
        return object : AbstractLogHelper<Item>(logService) {
            override fun call(): Item {
                storage[item.id] = item
                return storage[item.id] ?: throw IllegalArgumentException("생성 버그")
            }
        }.execute("ItemRepositoryTemplateMethod/saveItem")
    }
}