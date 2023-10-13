package com.vtb.hackathon.repository

import com.vtb.hackathon.domain.SimpleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SimpleRepository : JpaRepository<SimpleEntity, Long> {
}