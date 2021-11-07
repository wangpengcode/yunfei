package com.yunfei.finance.persistence.entity

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.math.BigDecimal
import java.math.BigInteger
import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "STAFF")
@EntityListeners(AuditingEntityListener::class)
data class Staff(
	@Id
	@Column(name = "ID", length = 128)
	val id: String,
	
	@Column(name = "NAME", length = 128)
	var name: String,
	
	@Column(name = "DEPARTMENT", length = 128)
	var department: String
)