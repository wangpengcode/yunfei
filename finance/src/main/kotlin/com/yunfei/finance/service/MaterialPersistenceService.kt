package com.yunfei.finance.service

import com.yunfei.finance.persistence.entity.Material
import com.yunfei.finance.persistence.repository.MaterialRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class MaterialPersistenceService(
	val materialRepository: MaterialRepository
) {
	val logger: Logger = LoggerFactory.getLogger(MaterialPersistenceService::class.java)
	
	fun save(materials: List<Material>): List<Material> {
		val list: ArrayList<Material> = arrayListOf()
		for (i in materials) {
			try {
				materialRepository.save(i)
			} catch (e: Exception) {
				logger.error("#MaterialPersistenceService#save error", e)
				list.add(i)
			}
		}
		return list
	}
}