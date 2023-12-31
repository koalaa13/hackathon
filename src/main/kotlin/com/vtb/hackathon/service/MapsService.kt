package com.vtb.hackathon.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.vtb.hackathon.configuration.ApiProperties
import com.vtb.hackathon.domain.MapPoint
import com.vtb.hackathon.domain.RouteInfo
import com.vtb.hackathon.domain.dto.PathsInfoDTO
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service

@Service
class MapsService(
    private val apiProperties: ApiProperties
) {
    val httpClient: OkHttpClient = OkHttpClient()
    val objectMapper: ObjectMapper = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    fun getRoute(from: MapPoint, to: MapPoint, profile: Profile = Profile.CAR): RouteInfo {
        val apiKey = apiProperties.key
        val lat1 = from.latitude
        val long1 = from.longitude
        val lat2 = to.latitude
        val long2 = to.longitude
        val request: Request = Request.Builder()
            .url("https://graphhopper.com/api/1/route?point=$lat1,$long1&point=$lat2,$long2&" +
                    "profile=${profile.description}&locale=de&calc_points=false&key=$apiKey")
            .get()
            .build()
        val response = httpClient.newCall(request).execute()
        val result: PathsInfoDTO = objectMapper.readValue(response.body?.string()!!)
        return RouteInfo(result.paths[0].distance, result.paths[0].time)
    }

    companion object {
        enum class Profile(val description: String) {
            FOOT("foot"),
            CAR("car")
        }
    }
}
