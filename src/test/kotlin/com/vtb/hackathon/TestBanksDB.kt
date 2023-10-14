package com.vtb.hackathon

import com.vtb.hackathon.domain.BankEntity
import com.vtb.hackathon.domain.MapPoint
import com.vtb.hackathon.domain.TaskEntity
import com.vtb.hackathon.repository.BankRepository
import com.vtb.hackathon.repository.TaskRepository
import com.vtb.hackathon.service.BankService
import com.vtb.hackathon.service.MapsService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TestBanksDB(
    @Autowired
    private val bankRepository: BankRepository,
    @Autowired
    private val taskRepository: TaskRepository,
    @Autowired
    private val bankService: BankService,
    @Autowired
    private val mapsService: MapsService,
) {

    @BeforeEach
    fun clearDB() {
        bankRepository.deleteAll()
    }

    @Test
    fun baseTest() {
        val dummyEntity = BankEntity("hui", 1.0, 2.0, "улица Пушкина")
        bankRepository.save(dummyEntity)
        val fromBase = bankRepository.findById(dummyEntity.id!!)
        Assertions.assertThat(dummyEntity).isEqualTo(fromBase.get())
    }

    @Test
    fun checkBankService() {
        val dummy = bankRepository.findAll()
        Assertions.assertThat(dummy.size).isEqualTo(0)
        val dummyEntity = BankEntity("hui", 1.0, 1.01, "улица Пушкина")
        bankRepository.save(dummyEntity)
        val point = MapPoint(1.0, 1.0)
        val banksList = bankService.getNearBanks(point)
        Assertions.assertThat(banksList.size).isEqualTo(1)
    }
    @Test
    fun checkManyToMany() {
        val dummyEntity = BankEntity("hui", 1.0, 1.0, "улица Пушкина")
        val dummyTask = TaskEntity("hui", 1.0)
        bankRepository.save(dummyEntity)
        taskRepository.save(dummyTask)

        val dummyBank2 = bankRepository.findById(dummyEntity.id!!).get()
        val dummyTask2 = taskRepository.findById(dummyTask.id!!).get()

        Assertions.assertThat(dummyBank2).isEqualTo(dummyEntity)
        Assertions.assertThat(dummyTask2).isEqualTo(dummyTask)

        dummyEntity.tasks.add(dummyTask2)
        dummyTask.banks.add(dummyBank2)
        bankRepository.save(dummyEntity)
        taskRepository.save(dummyTask)
        val getFromBaseTask = bankRepository.findById(dummyBank2.id!!).get().tasks
        val getFromBaseBank = taskRepository.findById(dummyTask2.id!!).get().banks
        Assertions.assertThat(getFromBaseBank.size).isEqualTo(1)
        Assertions.assertThat(getFromBaseTask.size).isEqualTo(1)
    }

    @Test
    fun checkMapsService() {
        val digit = mapsService.getRoute(MapPoint(59.9844894, 30.3170267), MapPoint(59.9698373, 30.340402))
        Assertions.assertThat(digit.distance).isEqualTo(4069.84)
    }
}