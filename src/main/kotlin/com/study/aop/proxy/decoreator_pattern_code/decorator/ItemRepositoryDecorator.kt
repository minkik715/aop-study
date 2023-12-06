package com.study.aop.proxy.decoreator_pattern_code.decorator

import com.study.aop.item.Item
import com.study.aop.logger.LogServiceDirtyCode
import com.study.aop.logger.data.Trace
import com.study.aop.logger.strategy_pattern.LogHelperStrategy
import com.study.aop.proxy.decoreator_pattern_code.ItemRepositoryRealSubject
import com.study.aop.proxy.decoreator_pattern_code.`interface`.ItemRepository
import org.springframework.stereotype.Repository

@Repository
class ItemRepositoryDecorator(
    override val logService: LogServiceDirtyCode
):LogDecorator(), ItemRepository {

    val itemRepositoryRealStrategy = ItemRepositoryRealSubject()

    override fun findItems(): List<Item> {
        var trace: Trace? = null

        return runCatching {
            trace = logService.begin("ItemRepository/findItems")
            itemRepositoryRealStrategy.findItems()
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!, it)
            throw it
        }.getOrThrow()
    }

    override fun saveItem(item: Item): Item {
        var trace: Trace? = null
        return runCatching {
            trace = logService.begin("ItemRepository/saveItem")
            itemRepositoryRealStrategy.saveItem(item)
        }.onSuccess {
            logService.finish(trace!!)
        }.onFailure {
            logService.execption(trace!!, it)
            throw it
        }.getOrThrow()
    }
}