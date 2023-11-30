package com.study.aop.item

import java.util.*

data class ItemDTO(
    var name: String,
    var price: Long =0L,
    var description: String ="",
){
    fun toItem(): Item {
        return Item(
            UUID.randomUUID().toString(),
            this.price,
            this.name,
            this.description
        )
    }
}