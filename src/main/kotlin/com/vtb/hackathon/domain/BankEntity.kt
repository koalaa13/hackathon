package com.vtb.hackathon.domain

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name="bank")
class BankEntity(
    val bankName: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
//    @ManyToMany val tasks: List<TaskEntity>
) : BaseAuditEntity<Long>() {
    fun set(mp: MapPoint) {
        this.latitude = mp.latitude
        this.longitude = mp.longitude
    }

    fun getPoint() = MapPoint(this.latitude, this.longitude)
}