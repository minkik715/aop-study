package com.study.aop.template_method_pattern_code

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.template_method_pattern.LogHelperTemplateMethod
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/template-method/item")
class ItemControllerTemplateMethod(
    private val itemService: ItemServiceTemplateMethod,
    private val logService: LogServiceDirtyCode
) {
    @GetMapping
    fun getItems(): List<Item> {
        return object : LogHelperTemplateMethod<List<Item>>(logService) {
            override fun call(): List<Item> {
                return itemService.getItems()
            }
        }.execute("ItemControllerDirtyCode/getItems")
    }

    @PostMapping
    fun createItem(@RequestBody item: ItemDTO): Item {
        return object : LogHelperTemplateMethod<Item>(logService) {
            override fun call(): Item {
                return itemService.createItem(item)
            }
        }.execute("ItemControllerDirtyCode/createItem")
    }
}