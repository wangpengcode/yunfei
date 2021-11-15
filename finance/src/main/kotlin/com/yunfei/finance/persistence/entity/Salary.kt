package com.yunfei.finance.persistence.entity

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.math.BigDecimal
import java.math.BigInteger
import java.sql.Date
import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "SALARY")
@EntityListeners(AuditingEntityListener::class)
data class Salary(
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", length = 64)
	val id: BigInteger? = null,
	
	@Column(name = "MATERIAL_ID", length = 128)
	var materialId: String,
	
	@Column(name = "STAFF_ID", length = 128)
	var staffId: String,
	
	@Column(name = "SALARY_DATE", length = 30)
	var salaryDate: String,
	
	@Column(name = "START")
	var start: String,
	
	@Column(name = "END")
	var end: String,
	
	@Column(name = "LAUNCH_DEDUCT")
	var launchDeduct: Boolean,
	
	@Column(name = "QUANTITY")
	var quantity: Int,
	
	@Column(name = "WORK_OVERTIME")
	var workOvertime: Boolean,
	
	@Column(name = "SUBSIDY")
	var subsidy: BigDecimal,
	
	@Column(name = "SALARY_SUMMARY")
	var salarySummary: BigDecimal,
	
	@Column(name = "WORK_TIME")
	var workTime: BigDecimal
)