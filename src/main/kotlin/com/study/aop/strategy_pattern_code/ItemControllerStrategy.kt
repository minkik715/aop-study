package com.study.aop.strategy_pattern_code

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.strategy_pattern.LogCallStrategy
import com.study.aop.logger.strategy_pattern.LogHelperStrategy
import com.study.aop.logger.template_method_pattern.LogHelperTemplateMethod
import com.study.aop.template_method_pattern_code.ItemServiceTemplateMethod
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*


@RestController
@Qualifier("ItemControllerTemplateMethod")
@RequestMapping("/api/strategy/item")
class ItemControllerStrategy(
    private val itemService: ItemServiceStrategy,
    private val logService: LogServiceDirtyCode
) {
    @GetMapping
    fun getItems(): List<Item> {
        return LogHelperStrategy(logService) {
            itemService.getItems()
        }.execute("ItemControllerStrategy/getItems")
    }


    @PostMapping
    fun createItem(@RequestBody item: ItemDTO): Item {
        return LogHelperStrategy(logService) {
            itemService.createItem(item)
        }.execute("ItemControllerDirtyCode/createItem")
    }
}