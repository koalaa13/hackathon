package com.vtb.hackathon

import com.vtb.hackathon.domain.BankEntity
import com.vtb.hackathon.domain.MapPoint
import com.vtb.hackathon.repository.BankRepository
import com.vtb.hackathon.service.BankService
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
        val dummyEntity = BankEntity("hui", 1.0, 2.0)
        bankRepository.save(dummyEntity)
        val point = MapPoint(1.0, 1.0)
        val banksList = bankService.getNearBanks(point)
        Assertions.assertThat(banksList.size).isEqualTo(1)
    }

    @Test
    fun checkBankController() {
        val dummyEntity = BankEntity("hui", 1.0, 2.0)
        bankRepository.save(dummyEntity)
        val point = MapPoint(1.0, 1.0)
        val banksList = bankService.getNearBanks(point)
        Assertions.assertThat(banksList.size).isEqualTo(1)

    }
}