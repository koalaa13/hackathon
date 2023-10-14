package com.vtb.hackathon.repository

import com.vtb.hackathon.domain.BankEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BankRepository: JpaRepository<BankEntity, Long> {
}