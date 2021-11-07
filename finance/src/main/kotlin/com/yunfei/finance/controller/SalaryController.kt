package com.yunfei.finance.controller

import com.yunfei.finance.persistence.entity.Salary
import com.yunfei.finance.service.SalaryPersistenceService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/salary")
class SalaryController(val salaryPersistenceService: SalaryPersistenceService) {
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
			throw e;
		}
	}
}