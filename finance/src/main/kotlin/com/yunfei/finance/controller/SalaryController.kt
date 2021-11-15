package com.yunfei.finance.controller

import com.yunfei.finance.dto.Response
import com.yunfei.finance.dto.SalaryUploadDto
import com.yunfei.finance.persistence.entity.Salary
import com.yunfei.finance.service.persistence.SalaryPersistenceService
import com.yunfei.finance.service.salary.SalaryProcessingService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/salary")
class SalaryController(
	val salaryPersistenceService: SalaryPersistenceService,
	val salaryProcessingService: SalaryProcessingService
) {
	val logger: Logger = LoggerFactory.getLogger(SalaryController::class.java)
	
	@GetMapping("/query/{staffId}/{day}")
	fun querySalaryByStaffIdAndDay(
		@PathVariable(value = "staffId") staffId: String,
		@PathVariable(value = "day") day: String
	): List<Salary> {
		try {
			logger.info("#SalaryController#querySalaryByStaffIdAndDay staffId = {}, day = {}", staffId, day)
			return salaryPersistenceService.queryByStaffIdAndDay(staffId, day)
		} catch (e: Exception) {
			logger.error("#SalaryController#querySalaryByStaffIdAndDay error:", e)
			throw e
		}
	}
	
	@PostMapping("/upload")
	fun uploadSalaryByStaffId(@RequestBody salaryUploadDto: SalaryUploadDto): Response<Salary> {
		return try {
			logger.info("#SalaryController#uploadSalaryByStaffId request {}", salaryUploadDto)
			val salary = salaryProcessingService.salaryProcessing(salaryUploadDto)
			Response(
				isSucceed = true,
				data = salary
			)
		} catch (e: Exception) {
			logger.error("#SalaryController#uploadSalaryByStaffId error:", e)
			Response(
				isSucceed = false,
				data = null,
				message = e.message
			)
		}
	}
}