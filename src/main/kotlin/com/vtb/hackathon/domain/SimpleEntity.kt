package com.vtb.hackathon.domain

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name="simple")
class SimpleEntity(val name: String) : BaseAuditEntity<Long>() {
}