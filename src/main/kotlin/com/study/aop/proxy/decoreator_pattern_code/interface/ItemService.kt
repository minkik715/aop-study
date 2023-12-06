package com.study.aop.proxy.decoreator_pattern_code.`interface`

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.strategy_pattern.LogHelperStrategy
import org.springframework.stereotype.Service

interface ItemService {
    fun getItems(): List<Item>

    fun createItem(item: ItemDTO): Item
}