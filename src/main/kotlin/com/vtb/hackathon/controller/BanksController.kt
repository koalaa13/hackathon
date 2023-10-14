package com.vtb.hackathon.controller

import com.vtb.hackathon.domain.MapPoint
import com.vtb.hackathon.service.BankService
import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/banks")
@AllArgsConstructor
class BanksController(val bankService: BankService) {

    @PostMapping("/near")
    @ResponseBody
    fun getNearBanks(mapPoint: MapPoint): String {
        return bankService.getNearBanks(mapPoint).toString()
    }
}