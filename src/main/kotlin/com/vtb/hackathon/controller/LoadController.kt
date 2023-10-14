package com.vtb.hackathon.controller

import com.vtb.hackathon.service.LoadService
import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/load")
@AllArgsConstructor
class LoadController(val loadService: LoadService) {

    @GetMapping("/{bankId}")
    @ResponseBody
    fun getLoadFromBank(@PathVariable bankId: Long): String {
        return loadService.getLoad(bankId).toString()
    }
}