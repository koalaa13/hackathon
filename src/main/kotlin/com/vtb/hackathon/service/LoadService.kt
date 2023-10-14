package com.vtb.hackathon.service

import com.vtb.hackathon.domain.LoadInfo
import com.vtb.hackathon.repository.TaskRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

@Service
class LoadService(val taskRepository: TaskRepository) {
    private val state = ConcurrentHashMap<Long, BankState>()

    fun getLoad(bankId: Long): LoadInfo {
        return LoadInfo(state[bankId]!!.calculateWaitingTime())
    }

    fun getAverageLoads(bankId: Long): List<LoadInfo> {
        return List(24) { _ -> LoadInfo(0.0) }
    }

    private class BankState {
        private val visitors = HashMap<Long, Long>()

        private var workerCount = AtomicInteger(0)

        fun addVisitor(taskId: Long) {
            synchronized(visitors) {
                visitors.put(taskId, visitors.getOrDefault(taskId, 0) + 1)
            }
        }

        fun removeVisitor(taskId: Long) {
            synchronized(visitors) {
                visitors.put(taskId, visitors[taskId]!! - 1)
            }
        }

        fun addWorker() = workerCount.incrementAndGet()

        fun removeWorker() = workerCount.decrementAndGet()

        fun calculateWaitingTime(): Double {
            val averageTimes = taskRepository.findAll()
            synchronized(visitors) {
                val sumTime = 0.0
                visitors.forEach { (taskId, taskCount) -> sumTime += averageTimes[taskId] * taskCount }
                return sumTime / workerCount.get()
            }
        }
    }
}
