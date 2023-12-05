package com.study.aop.strategy_pattern_code

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.strategy_pattern.LogHelperStrategy
import org.springframework.stereotype.Service

@Service
class ItemServiceStrategy(
    private val itemRepository: ItemRepositoryStrategy,
    private val logService: LogServiceDirtyCode
) {
    fun getItems(): List<Item> {
        return LogHelperStrategy(logService) {
            itemRepository.findItems()
        }.execute("ItemRepositoryTemplateMethod/findItems")
    }

    fun createItem(item: ItemDTO): Item {
        return LogHelperStrategy(logService) {
            itemRepository.saveItem(item.toItem())
        }.execute("ItemServiceDirtyCode/createItem")
    }
}