package com.yunfei.finance.controller

import com.yunfei.finance.annotation.Column
import com.yunfei.finance.extensions.excelToList
import com.yunfei.finance.persistence.entity.Staff
import com.yunfei.finance.service.persistence.StaffPersistenceService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartHttpServletRequest
import kotlin.streams.toList

@RestController
@RequestMapping("/staff")
class StaffController(val staffPersistenceService: StaffPersistenceService) {
	val logger: Logger = LoggerFactory.getLogger(StaffController::class.java)
	
	@GetMapping("/all")
	fun allStaff(): List<Staff> {
		val list = staffPersistenceService.findAll()
		list.forEach { println(it.id) }
		return list
	}
	
	@PostMapping("/upload")
	fun upload(request: MultipartHttpServletRequest): ResponseEntity<List<Staff>> {
		var notUpload: List<Staff> = arrayListOf()
		val file = request.getFiles("file")[0]
		
		val list: List<StaffExcel> = excelToList(
			file.inputStream,
			StaffExcel::class
		)
		logger.info(" excel "+ list.size)
		list.forEach { print(it.id) }
		val staffs = list.stream().map {
			Staff(it.id, it.name, it.department)
		 }.toList()
		notUpload = staffPersistenceService.save(staffs)
		return ResponseEntity.status(HttpStatus.OK).body(notUpload)
	}
	
	class StaffExcel {
		@Column(0)
		var id: String = ""
		
		@Column(1)
		var name: String = ""
		
		@Column(2)
		var department: String = ""
	}
}