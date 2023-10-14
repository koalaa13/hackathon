package com.vtb.hackathon.domain

import java.time.Duration

class RouteInfo (
        val from: MapPoint,
        val to: MapPoint,
        val distance: Double,
        val time: Duration)
