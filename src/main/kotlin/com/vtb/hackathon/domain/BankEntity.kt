package com.vtb.hackathon.domain

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name="bank")
class BankEntity(
    val bankName: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,

    @ManyToMany
    @JoinTable(
        name = "bank_task",
        joinColumns = [JoinColumn(name = "bank_id")],
        inverseJoinColumns = [JoinColumn(name = "task_id")]
    )
    val tasks: MutableSet<TaskEntity> = mutableSetOf()
) : BaseAuditEntity<Long>() {
     fun getPoint() = MapPoint(this.latitude, this.longitude)
}