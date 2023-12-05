package com.study.aop.thread_local_dirty_code

import com.study.aop.item.Item
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.data.Trace
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

@Repository
class ItemRepositoryDirtyCode(
    private val logService: LogServiceDirtyCode
) {

    val storage = hashMapOf<String, Item>()

    fun findItems(): List<Item> {
        var trace: Trace? = null

        return runCatching {
            trace = logService.begin("ItemRepositoryDirtyCode/findItems")
            storage.values.toList()
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!)
            throw it
        }.getOrThrow()
    }

    fun saveItem(item: Item): Item {
        var trace: Trace? = null
        return runCatching {
            trace = logService.begin("ItemRepositoryDirtyCode/saveItem")
            storage[item.id] = item
            storage[item.id] ?: throw IllegalArgumentException("생성 버그")
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!)
            throw it
        }.getOrThrow()

    }
}