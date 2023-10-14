package com.vtb.hackathon.service

import com.vtb.hackathon.domain.BankEntity
import com.vtb.hackathon.domain.MapPoint
import com.vtb.hackathon.repository.BankRepository
import org.springframework.stereotype.Service
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@Service
class BankService(val bankRepository: BankRepository) {
    private val radiusEarth = 6372

    private fun toRad(value: Double): Double {
        return value * Math.PI / 180
    }

    private fun distance(point1: MapPoint, point2: MapPoint): Double {
        val dLat = toRad(point2.latitude - point1.latitude)
        val dLon = toRad(point2.longitude - point1.longitude)
        val lat1 = toRad(point1.latitude)
        val lat2 = toRad(point2.latitude)

        val a = sin(dLat / 2) * sin(dLat / 2) +
                sin(dLon / 2) * sin(dLon / 2) * cos(lat1) * cos(lat2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return radiusEarth * c
    }

    fun getAllBanks(): List<BankEntity> {
        return bankRepository.findAll()
    }

    fun getNearBanks(center: MapPoint): List<BankEntity> {
        return getNearBanks(center, radius = 5.0)
    }

    fun getNearBanks(center: MapPoint, radius: Double): List<BankEntity> {
        return bankRepository.findAll().filter { bank -> isNear(center, bank, radius) }
    }

    private fun isNear(center: MapPoint, bank: BankEntity, radius: Double): Boolean {
        val distance = distance(center, bank.getPoint())
        return distance <= radius
    }
}
