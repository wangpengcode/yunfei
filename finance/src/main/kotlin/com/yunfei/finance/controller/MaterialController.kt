package com.yunfei.finance.controller

import com.yunfei.finance.annotation.Column
import com.yunfei.finance.extensions.excelToList
import com.yunfei.finance.persistence.entity.Material
import com.yunfei.finance.service.persistence.MaterialPersistenceService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartHttpServletRequest
import java.math.BigDecimal
import kotlin.streams.toList

@RestController
@RequestMapping("/material")
class MaterialController(
	val materialPersistenceService: MaterialPersistenceService
) {
	val logger: Logger = LoggerFactory.getLogger(MaterialController::class.java)
	
	@PostMapping("/upload")
	fun upload(request: MultipartHttpServletRequest): ResponseEntity<List<Material>> {
		var notUpload: List<Material> = arrayListOf()
		val file = request.getFiles("file")[0]
		
		val list: List<MaterialExcel> = excelToList(
			file.inputStream,
			MaterialExcel::class
		)
		logger.info(" excel " + list.size)
		list.forEach { print(it.materialId) }
		val materials = list.stream().map {
			Material(it.materialId, it.drawingId, it.processingId, it.processingName, it.unitPrice.toBigDecimal(), it.quota.toBigDecimal())
		}.toList()
		notUpload = materialPersistenceService.save(materials)
		return ResponseEntity.status(HttpStatus.OK).body(notUpload)
	}
	
	class MaterialExcel {
		@Column(0)
		val materialId: String = ""
		
		@Column(1)
		var drawingId: String = ""
		
		@Column(2)
		var processingId: String = ""
		
		@Column(3)
		var processingName: String = ""
		
		@Column(4)
		var unitPrice: String = ""
		
		@Column(5)
		var quota: String = ""
	}
}