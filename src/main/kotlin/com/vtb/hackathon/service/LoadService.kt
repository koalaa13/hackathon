package com.vtb.hackathon.service

import com.vtb.hackathon.domain.LoadInfo
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class LoadService {
    fun getLoad(bank: Any): LoadInfo {
        return getLoad(bank, LocalDateTime.now())
    }

    fun getLoad(bank: Any, time: LocalDateTime): LoadInfo {
        return LoadInfo(0.0)
    }

    fun getLoads(bank: Any): List<LoadInfo> {
        return List(24) { _ -> LoadInfo(0.0) }
    }
}
