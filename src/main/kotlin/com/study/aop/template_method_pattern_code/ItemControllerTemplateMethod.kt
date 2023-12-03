package com.study.aop.thread_local_dirty_code

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.data.Trace
import com.study.aop.logger.template_method_pattern.AbstractLogHelper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*


@RestController
@Qualifier("ItemControllerTemplateMethod")
@RequestMapping("/api/template-method/item")
class ItemControllerTemplateMethod(
    private val itemService: ItemServiceTemplateMethod,
    private val logService: LogServiceDirtyCode
) {
    @GetMapping
    fun getItems(): List<Item> {
        return object : AbstractLogHelper<List<Item>>(logService) {
            override fun call(): List<Item> {
                return itemService.getItems()
            }
        }.execute("ItemControllerDirtyCode/getItems")
    }

    @PostMapping
    fun createItem(@RequestBody item: ItemDTO): Item {
        return object : AbstractLogHelper<Item>(logService) {
            override fun call(): Item {
                return itemService.createItem(item)
            }
        }.execute("ItemControllerDirtyCode/createItem")
    }
}