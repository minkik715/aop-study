package com.study.aop.item

import org.springframework.stereotype.Service

@Service
class ItemServiceBasic(
    private val itemRepository: ItemRepositoryBasic
) {
    fun getItems(): List<Item> {
        return itemRepository.findItems()
    }

    fun createItem(item: ItemDTO): Item {
        return itemRepository.saveItem(item.toItem())
    }
}