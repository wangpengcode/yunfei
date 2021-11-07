package com.yunfei.finance.persistence.entity

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "MATERIAL")
@EntityListeners(AuditingEntityListener::class)
data class Material (
	@Id
	@Column(name = "MATERIAL_ID", length = 128)
	val materialId: String,
	
	@Column(name = "DRAWING_ID", length = 128)
	var drawingId: String,
	
	@Column(name = "PROCESSING_ID", length = 128)
	var processingId: String,
	
	@Column(name = "PROCESSING_NAME", length = 128)
	var processingName: String,
	
	@Column(name = "UNIT_PRICE")
	var unitPrice: BigDecimal,
	
	@Column(name = "QUOTA")
	var quota: BigDecimal
)