package com.study.aop.item

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/item")
class ItemControllerBasic(
    private val itemService: ItemServiceBasic
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