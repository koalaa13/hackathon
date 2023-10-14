package com.vtb.hackathon.controller

import com.vtb.hackathon.domain.MapPoint
import com.vtb.hackathon.domain.RouteInfo
import com.vtb.hackathon.domain.SimpleEntity
import com.vtb.hackathon.service.MapsService
import com.vtb.hackathon.service.SimpleService
import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/simple")
@AllArgsConstructor
class SimpleController(var simpleService: SimpleService, var mapsService: MapsService) {

    @GetMapping("/check")
    fun check(): RouteInfo {
        return mapsService.getRoute(MapPoint(59.9844894, 30.3170267), MapPoint(59.9698373, 30.340402))
    }

    @GetMapping("/all")
    fun findAllSimples(): List<SimpleEntity> {
        return simpleService.findAll()
    }

    @PostMapping("/new")
    fun addNewSimple(@RequestBody name: String) {
        simpleService.saveNew(name)
    }
}