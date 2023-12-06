package com.study.aop.proxy.decoreator_pattern_code.`interface`

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.strategy_pattern.LogHelperStrategy
import org.springframework.web.bind.annotation.*

@ResponseBody
@RequestMapping("/api/decorator/item")
interface ItemController {

    @GetMapping
    fun getItems(): List<Item>


    @PostMapping
    fun createItem(@RequestBody item: ItemDTO): Item
}