package com.study.aop.proxy.decoreator_pattern_code

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.strategy_pattern.LogHelperStrategy
import com.study.aop.proxy.decoreator_pattern_code.`interface`.ItemController
import com.study.aop.proxy.decoreator_pattern_code.`interface`.ItemService
import org.springframework.web.bind.annotation.*


class ItemControllerRealSubject(
    private val itemService: ItemService,
) : ItemController {

    @GetMapping
    override fun getItems(): List<Item> {
        return itemService.getItems()
    }


    @PostMapping
    override fun createItem(@RequestBody item: ItemDTO): Item {
        return itemService.createItem(item)
    }
}