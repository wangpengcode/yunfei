package com.yunfei.finance.service

import com.yunfei.finance.persistence.entity.Salary
import com.yunfei.finance.persistence.repository.SalaryRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SalaryPersistenceService(
	val salaryRepository: SalaryRepository
) {
	val logger: Logger = LoggerFactory.getLogger(SalaryPersistenceService::class.java)
	
	fun queryByStaffIdAndDay(staffId: String, day: String): List<Salary> {
		try {
			return salaryRepository.queryByStaffIdAndDay(staffId, day);
		} catch (e: Exception) {
			logger.error("#SalaryPersistenceService#queryByStaffIdAndDay", e)
			throw e;
		}
	}
	
}