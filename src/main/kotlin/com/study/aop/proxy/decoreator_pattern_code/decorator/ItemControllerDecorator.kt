package com.study.aop.proxy.decoreator_pattern_code.decorator

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.data.Trace
import com.study.aop.proxy.decoreator_pattern_code.ItemControllerRealSubject
import com.study.aop.proxy.decoreator_pattern_code.`interface`.ItemController
import com.study.aop.proxy.decoreator_pattern_code.`interface`.ItemService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/decorator/item")
class ItemControllerDecorator(
    private val itemService: ItemService,
    override val logService: LogServiceDirtyCode,
) : LogDecorator(), ItemController {
    private val itemControllerRealSubject = ItemControllerRealSubject(itemService)

    override fun getItems(): List<Item> {
        var trace: Trace? = null

        return runCatching {
            trace = logService.begin("ItemController/getItems")
            itemControllerRealSubject.getItems()
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!, it)
            throw it
        }.getOrThrow()
    }


    override fun createItem(@RequestBody item: ItemDTO): Item {
        var trace: Trace? = null

        return runCatching {
            trace = logService.begin("ItemController/createItem")
            itemControllerRealSubject.createItem(item)
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!, it)
            throw it
        }.getOrThrow()
    }
}