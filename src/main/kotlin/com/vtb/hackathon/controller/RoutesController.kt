package com.vtb.hackathon.controller

import com.vtb.hackathon.domain.MapPoint
import com.vtb.hackathon.service.DistanceService
import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/routes")
@AllArgsConstructor
class RoutesController(val distanceService: DistanceService) {

    @PostMapping("/")
    @ResponseBody
    fun getNearBanks(clientRouteInfo: ClientRouteInfo): String {
        return distanceService.getRoutes(clientRouteInfo.from).toString()
    }

    data class ClientRouteInfo(val from: MapPoint, val tasks: List<Int>)

}