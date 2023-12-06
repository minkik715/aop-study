package com.study.aop.proxy.decoreator_pattern_code.decorator

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.data.Trace
import com.study.aop.logger.strategy_pattern.LogHelperStrategy
import com.study.aop.proxy.decoreator_pattern_code.ItemServiceRealSubject
import com.study.aop.proxy.decoreator_pattern_code.`interface`.ItemRepository
import com.study.aop.proxy.decoreator_pattern_code.`interface`.ItemService
import org.springframework.stereotype.Service

@Service
class ItemServiceDecorator(
    private val itemRepository: ItemRepository,
    override val logService: LogServiceDirtyCode,
) :LogDecorator(), ItemService {

    private val itemServiceRealSubject: ItemService = ItemServiceRealSubject(itemRepository)

    override fun getItems(): List<Item> {
        var trace: Trace? = null

        return kotlin.runCatching {
            trace = logService.begin("ItemService/getItems")
            itemServiceRealSubject.getItems()
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!, it)
            throw it
        }.getOrThrow()

    }

    override fun createItem(item: ItemDTO): Item {
        var trace: Trace? = null
        return runCatching {
            trace = logService.begin("ItemService/createItem")
            itemServiceRealSubject.createItem(item)
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!, it)
            throw it
        }.getOrThrow()
    }
}