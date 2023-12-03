package com.study.aop.thread_local_dirty_code

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.data.Trace
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*


@RestController
@Qualifier("ItemControllerDirtyCode")
@RequestMapping("/api/dirty-code/item")
class ItemControllerDirtyCode(
    private val itemService: ItemServiceDirtyCode,
    private val logService: LogServiceDirtyCode
) {


    @GetMapping
    fun getItems(): List<Item> {
        var trace: Trace? = null

        return runCatching {
            trace = logService.begin("ItemControllerDirtyCode/getItems")
            itemService.getItems()
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!)
            throw it
        }.getOrThrow()
    }

    @PostMapping
    fun createItem(@RequestBody item: ItemDTO): Item {
        var trace: Trace? = null

        return runCatching {
            trace = logService.begin("ItemControllerDirtyCode/createItem")
            itemService.createItem(item)
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!)
            throw it
        }.getOrThrow()
    }

}