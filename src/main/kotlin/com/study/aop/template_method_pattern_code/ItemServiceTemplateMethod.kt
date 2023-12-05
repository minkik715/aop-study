package com.study.aop.template_method_pattern_code

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.template_method_pattern.LogHelperTemplateMethod
import com.study.aop.template_method_pattern_code.ItemRepositoryTemplateMethod
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class ItemServiceTemplateMethod(
    private val itemRepository: ItemRepositoryTemplateMethod,
    private val logService: LogServiceDirtyCode
) {
    fun getItems(): List<Item> {

        return object : LogHelperTemplateMethod<List<Item>>(logService) {
            override fun call(): List<Item> {
                return itemRepository.findItems()

            }
        }.execute("ItemRepositoryTemplateMethod/findItems")
    }

    fun createItem(item: ItemDTO): Item {
        return object : LogHelperTemplateMethod<Item>(logService) {
            override fun call(): Item {
                return itemRepository.saveItem(item.toItem())
            }
        }.execute("ItemServiceDirtyCode/createItem")
    }
}