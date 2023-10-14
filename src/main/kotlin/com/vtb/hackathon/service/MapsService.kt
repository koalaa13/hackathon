package com.vtb.hackathon.service

import com.vtb.hackathon.domain.MapPoint
import com.vtb.hackathon.domain.RouteInfo
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class MapsService {
    fun getRoute(from: MapPoint, to: MapPoint): RouteInfo {
        return RouteInfo(from, to, 1.0, Duration.ofMinutes(1))
    }
}
