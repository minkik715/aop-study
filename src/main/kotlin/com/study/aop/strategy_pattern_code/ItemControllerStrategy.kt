package com.study.aop.strategy_pattern_code

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.strategy_pattern.LogHelperStrategy
import org.springframework.web.bind.annotation.*


@RestController
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