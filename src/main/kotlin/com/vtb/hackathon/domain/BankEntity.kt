package com.vtb.hackathon.domain

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name="banks")
class BankEntity(
    val bankName: String,
    var latitude: Double,
    var longitude: Double,
) : BaseAuditEntity<Long>() {
    fun set(mp: MapPoint) {
        this.latitude = mp.lat
        this.longitude = mp.long
    }
}