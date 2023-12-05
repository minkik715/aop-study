package com.study.aop.template_callback_pattern_code

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.template_call_back_pattern.LogHelperCallback
import org.springframework.stereotype.Service

@Service
class ItemServiceTemplateCallback(
    private val itemRepository: ItemRepositoryTemplateCallback,
    private val logService: LogServiceDirtyCode
) {
    fun getItems(): List<Item> {
        return LogHelperCallback<List<Item>>(logService).execute("ItemRepositoryTemplateMethod/findItems") {
            itemRepository.findItems()
        }
    }

    fun createItem(item: ItemDTO): Item {
        return LogHelperCallback<Item>(logService).execute("ItemServiceDirtyCode/createItem") {
            itemRepository.saveItem(item.toItem())
        }
    }
}