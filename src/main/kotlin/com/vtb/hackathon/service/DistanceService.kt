package com.vtb.hackathon.service

import com.vtb.hackathon.domain.MapPoint
import com.vtb.hackathon.domain.RouteInfo
import org.springframework.stereotype.Service

@Service
class DistanceService(var loadService: LoadService, var bankService: BankService, var mapsService: MapsService) {

    fun getRoutes(from: MapPoint): List<RouteInfo> {
        val nearestBanks = bankService.getNearBanks(from)
        val bankPoints = nearestBanks.map { bank -> MapPoint(bank.latitude, bank.longitude) }
        return bankPoints.map { bank -> mapsService.getRoute(from, bank) }
    }
}
