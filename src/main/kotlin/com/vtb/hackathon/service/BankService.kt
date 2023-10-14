package com.vtb.hackathon.service

import com.vtb.hackathon.domain.BankEntity
import com.vtb.hackathon.domain.MapPoint
import com.vtb.hackathon.repository.BankRepository
import org.springframework.stereotype.Service
import kotlin.math.sqrt

@Service
class BankService(val bankRepository: BankRepository) {

    fun getNearBanks(center: MapPoint): List<BankEntity> {
        return getNearBanks(center, radius = 5.0)
    }

    private fun getNearBanks(center: MapPoint, radius: Double): List<BankEntity> {
        return bankRepository.findAll().filter { bank -> isNear(center, bank, radius) }
    }

    private fun isNear(center: MapPoint, bank: BankEntity, radius: Double): Boolean {
        val distance = getDistance(center, bank)
        return distance <= radius
    }

    private fun getDistance(center: MapPoint, bank: BankEntity): Double {
        val x = center.latitude - bank.latitude
        val y = center.longitude - bank.longitude
        return sqrt(x * x + y * y)
    }
}
