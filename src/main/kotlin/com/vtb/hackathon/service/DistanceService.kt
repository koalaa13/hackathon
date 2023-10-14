package com.vtb.hackathon.service

import com.vtb.hackathon.domain.BankEntity
import com.vtb.hackathon.domain.MapPoint
import com.vtb.hackathon.domain.RouteInfo
import org.springframework.stereotype.Service

@Service
class DistanceService(var loadService: LoadService, var bankService: BankService, var mapsService: MapsService) {
    private fun getBestRoutesByProfile(from: MapPoint, profile: MapsService.Companion.Profile): List<RouteInfo> {
        val nearestBanks = if (profile == MapsService.Companion.Profile.FOOT) {
            bankService.getNearBanks(from, radius = 5.0)
        } else {
            bankService.getNearBanks(from, radius = 20.0)
        }
        return nearestBanks.map {
            bank -> mapsService.getRoute(from, bank.getPoint(), profile)
        } .sortedBy { route -> route.time } .take(3)
    }

    fun getRoutes(from: MapPoint): List<RouteInfo> {
        val footRoutes = getBestRoutesByProfile(from, MapsService.Companion.Profile.FOOT)
        val carRoutes = getBestRoutesByProfile(from, MapsService.Companion.Profile.CAR)
        return footRoutes + carRoutes
    }
}
