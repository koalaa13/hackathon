package com.vtb.hackathon.repository

import com.vtb.hackathon.domain.SimpleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository: JpaRepository<SimpleEntity, Long> {
}