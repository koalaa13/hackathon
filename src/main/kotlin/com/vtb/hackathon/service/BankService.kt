package com.vtb.hackathon.service

import com.vtb.hackathon.domain.BankEntity
import com.vtb.hackathon.domain.MapPoint
import org.springframework.stereotype.Service

@Service
class BankService {
    fun getBanks(center: MapPoint): List<BankEntity> {
        return getBanks(center, 5.0)
    }

    fun getBanks(center: MapPoint, radius: Double): List<BankEntity> {
        return listOf(BankEntity("1", 0.0, 0.0))
                .filter { bank ->
                    (bank.longitude - center.long) + (bank.latitude - center.lat) < radius
                }
    }
}
