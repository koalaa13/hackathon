package com.vtb.hackathon.domain

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name="banks")
class BankEntity(
    val bankName: String,
    val latitude: Double,
    val longitude: Double,
) : BaseAuditEntity<Long>() {}