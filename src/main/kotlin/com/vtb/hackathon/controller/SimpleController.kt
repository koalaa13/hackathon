package com.vtb.hackathon.controller

import com.vtb.hackathon.domain.SimpleEntity
import com.vtb.hackathon.service.SimpleService
import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/simple")
@AllArgsConstructor
class SimpleController(var simpleService: SimpleService) {

    @GetMapping("/check")
    fun check(): String {
        return "ok"
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