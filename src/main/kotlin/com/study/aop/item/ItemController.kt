package com.study.aop.item

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/item")
class ItemController(
    private val itemService: ItemService
) {


    @GetMapping
    fun getItems(): List<Item> {
        return itemService.getItems()
    }

    @PostMapping
    fun createItem(@RequestBody item: ItemDTO) : Item{
        return itemService.createItem(item)
    }

}