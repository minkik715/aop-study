package com.study.aop.item

import org.springframework.stereotype.Service

@Service
class ItemService(
    private val itemRepository: ItemRepository
) {
    fun getItems(): List<Item> {
        return itemRepository.findItems()
    }

    fun createItem(item: ItemDTO): Item {
        return itemRepository.saveItem(item.toItem())
    }
}