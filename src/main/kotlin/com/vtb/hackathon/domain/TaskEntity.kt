package com.vtb.hackathon.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import jakarta.transaction.Transactional

@Entity
@Table(name="task")
class TaskEntity(
    val taskName: String,
    val avgTime: Double,
    @ManyToMany(mappedBy = "tasks")
    @JsonIgnore
    val banks: MutableSet<BankEntity> = mutableSetOf()
) : BaseAuditEntity<Long>()