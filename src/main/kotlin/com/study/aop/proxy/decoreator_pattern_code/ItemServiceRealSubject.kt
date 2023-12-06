package com.study.aop.proxy.decoreator_pattern_code

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.strategy_pattern.LogHelperStrategy
import com.study.aop.proxy.decoreator_pattern_code.`interface`.ItemRepository
import com.study.aop.proxy.decoreator_pattern_code.`interface`.ItemService
import org.springframework.stereotype.Service

class ItemServiceRealSubject(
    private val itemRepository: ItemRepository,
) : ItemService {

    override fun getItems(): List<Item> {
        return itemRepository.findItems()
    }

    override fun createItem(item: ItemDTO): Item {
        return itemRepository.saveItem(item.toItem())
    }
}