package com.vtb.hackathon.controller

import com.vtb.hackathon.domain.BankEntity
import com.vtb.hackathon.domain.MapPoint
import com.vtb.hackathon.service.BankService
import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/banks")
@AllArgsConstructor
@CrossOrigin(origins = ["*"])
class BanksController(val bankService: BankService) {

    @GetMapping("/all")
    @ResponseBody
    fun getAllBanks(): List<BankEntity> {
        return bankService.getAllBanks()
    }

    @PostMapping("/near")
    @ResponseBody
    fun getNearBanks(mapPoint: MapPoint): String {
        return bankService.getNearBanks(mapPoint).toString()
    }
}