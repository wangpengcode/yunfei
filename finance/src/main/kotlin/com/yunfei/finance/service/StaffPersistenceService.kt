package com.yunfei.finance.service

import com.yunfei.finance.persistence.entity.Staff
import com.yunfei.finance.persistence.repository.StaffRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class StaffPersistenceService(
	val staffRepository: StaffRepository
) {
	val logger: Logger = LoggerFactory.getLogger(StaffPersistenceService::class.java)
	
	fun save(staffs: List<Staff>): List<Staff> {
		val list: ArrayList<Staff> = arrayListOf()
		for (i in staffs) {
			try {
				staffRepository.save(i)
			} catch (e: Exception) {
				logger.error("#StaffPersistenceService#save error", e)
				list.add(i)
			}
		}
		return list
	}
	
	fun findAll() : List<Staff>{
		return staffRepository.findAll().toList()
	}
}