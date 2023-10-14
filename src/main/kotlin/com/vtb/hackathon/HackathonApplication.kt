package com.vtb.hackathon

import com.vtb.hackathon.configuration.ApiProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties(ApiProperties::class)
class HackathonApplication

fun main(args: Array<String>) {
	runApplication<HackathonApplication>(*args)
}
