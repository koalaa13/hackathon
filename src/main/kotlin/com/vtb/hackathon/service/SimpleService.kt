package com.vtb.hackathon.service

import com.vtb.hackathon.domain.SimpleEntity
import com.vtb.hackathon.repository.SimpleRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class SimpleService(var simpleRepository: SimpleRepository) {

    fun findAll(): List<SimpleEntity> {
        return simpleRepository.findAll()
    }

    fun saveNew(name: String) {
        simpleRepository.save(SimpleEntity(name))
    }
}