package com.study.aop.strategy_pattern_code

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.strategy_pattern.LogHelperStrategy
import com.study.aop.logger.template_method_pattern.LogHelperTemplateMethod
import com.study.aop.template_method_pattern_code.ItemRepositoryTemplateMethod
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
@Qualifier("ItemServiceTemplateMethod")
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