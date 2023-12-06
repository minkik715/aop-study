package com.study.aop.thread_local_dirty_code

import com.study.aop.item.Item
import com.study.aop.item.ItemDTO
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.data.Trace
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class ItemServiceDirtyCode(
    private val itemRepository: ItemRepositoryDirtyCode,
    private val logService: LogServiceDirtyCode
) {
    fun getItems(): List<Item> {
        var trace: Trace? = null

        return runCatching {
            trace = logService.begin("ItemRepositoryDirtyCode/getItems")
            itemRepository.findItems()
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!, it)
            throw it
        }.getOrThrow()
    }

    fun createItem(item: ItemDTO): Item {
        var trace: Trace? = null

        return runCatching {
            trace = logService.begin("ItemRepositoryDirtyCode/createItem")
            itemRepository.saveItem(item.toItem())
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!, it)
            throw it
        }.getOrThrow()
    }
}