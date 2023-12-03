package com.study.aop.thread_local_dirty_code

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.data.Trace
import com.study.aop.logger.template_method_pattern.AbstractLogHelper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
@Qualifier("ItemServiceTemplateMethod")
class ItemServiceTemplateMethod(
    private val itemRepository: ItemRepositoryTemplateMethod,
    private val logService: LogServiceDirtyCode
) {
    fun getItems(): List<Item> {

        return object : AbstractLogHelper<List<Item>>(logService) {
            override fun call(): List<Item> {
                return itemRepository.findItems()

            }
        }.execute("ItemRepositoryTemplateMethod/findItems")
    }

    fun createItem(item: ItemDTO): Item {
        return object : AbstractLogHelper<Item>(logService) {
            override fun call(): Item {
                return itemRepository.saveItem(item.toItem())
            }
        }.execute("ItemServiceDirtyCode/createItem")
    }
}