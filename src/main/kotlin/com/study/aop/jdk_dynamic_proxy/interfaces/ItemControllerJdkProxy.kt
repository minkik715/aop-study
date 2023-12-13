package com.study.aop.jdk_dynamic_proxy.interfaces

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/jdk-proxy/item")
interface ItemControllerJdkProxy {

    @GetMapping
    fun getItems(): List<Item>


    @PostMapping
    fun createItem(@RequestBody item: ItemDTO): Item
}