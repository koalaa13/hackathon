package com.vtb.hackathon.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@ConfigurationProperties(prefix = "api")
class ApiProperties {
    lateinit var key: String
}