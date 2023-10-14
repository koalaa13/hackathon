package com.vtb.hackathon.domain

import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name="task")
class TaskEntity(
    val taskName: String,
    val avgTime: Double,
//    @ManyToMany val tasks: List<TaskEntity>,
) : BaseAuditEntity<Long>()