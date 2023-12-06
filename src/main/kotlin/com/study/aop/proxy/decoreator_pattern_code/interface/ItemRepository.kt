package com.study.aop.proxy.decoreator_pattern_code.`interface`

import com.study.aop.item.Item
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.strategy_pattern.LogHelperStrategy
import org.springframework.stereotype.Repository
import java.util.HashMap

interface ItemRepository {

    fun findItems(): List<Item>

    fun saveItem(item: Item): Item
}