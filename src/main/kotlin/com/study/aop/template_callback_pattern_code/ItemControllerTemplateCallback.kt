package com.study.aop.template_callback_pattern_code

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.template_call_back_pattern.LogCallCallBack
import com.study.aop.logger.template_call_back_pattern.LogHelperCallback
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/strategy/item")
class ItemControllerTemplateCallback(
    private val itemService: ItemServiceTemplateCallback,
    private val logService: LogServiceDirtyCode
) {
    @GetMapping
    fun getItems(): List<Item> {
        return LogHelperCallback<List<Item>>(logService).execute(
            "ItemControllerStrategy/getItems"
        ) { itemService.getItems() }
    }


    @PostMapping
    fun createItem(@RequestBody item: ItemDTO): Item {
        return LogHelperCallback<Item>(logService).execute("ItemControllerDirtyCode/createItem") {
            itemService.createItem(item)
        }
    }
}