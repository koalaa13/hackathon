package com.vtb.hackathon

import com.vtb.hackathon.domain.BankEntity
import com.vtb.hackathon.repository.BankRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TestBanksDB(
    @Autowired
    private val bankRepository: BankRepository
) {

    @Test
    fun baseTest() {
        val dummyEntity = BankEntity("hui", 1.0, 2.0)
        bankRepository.save(dummyEntity)
        val fromBase = bankRepository.findById(dummyEntity.id!!)
        Assertions.assertThat(dummyEntity).isEqualTo(fromBase.get())
    }
}