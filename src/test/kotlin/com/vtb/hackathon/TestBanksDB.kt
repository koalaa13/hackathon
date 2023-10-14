package com.vtb.hackathon

import com.vtb.hackathon.domain.BankEntity
import com.vtb.hackathon.domain.MapPoint
import com.vtb.hackathon.repository.BankRepository
import com.vtb.hackathon.service.BankService
import com.vtb.hackathon.service.MapsService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TestBanksDB(
    @Autowired
    private val bankRepository: BankRepository,
    @Autowired
    private val bankService: BankService,
    @Autowired
    private val mapsService: MapsService,
) {

    @Test
    fun baseTest() {
        val dummyEntity = BankEntity("hui", 1.0, 2.0)
        bankRepository.save(dummyEntity)
        val fromBase = bankRepository.findById(dummyEntity.id!!)
        Assertions.assertThat(dummyEntity).isEqualTo(fromBase.get())
    }

    @Test
    fun checkBankService() {
        val dummyEntity = BankEntity("hui", 1.0, 1.01)
        bankRepository.save(dummyEntity)
        val point = MapPoint(1.0, 1.0)
        val banksList = bankService.getNearBanks(point)
        Assertions.assertThat(banksList.size).isEqualTo(1)
    }

    @Test
    fun checkMapsService() {
        val digit = mapsService.getRoute(MapPoint(59.9844894, 30.3170267), MapPoint(59.9698373, 30.340402))
        Assertions.assertThat(digit.distance).isEqualTo(4069.84)
    }
}