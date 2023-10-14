package com.vtb.hackathon.repository

import com.vtb.hackathon.domain.SimpleEntity
import com.vtb.hackathon.domain.TaskEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository: JpaRepository<TaskEntity, Long> {
}