package com.study.aop.item

import java.util.*

data class Item(
    val id : String = UUID.randomUUID().toString(),
    var price: Long,
    var name: String,
    var description: String,
)
