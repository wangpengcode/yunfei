package com.yunfei.finance.persistence.repository

import com.yunfei.finance.persistence.entity.Material
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface MaterialRepository: CrudRepository<Material, String> {
	@Query("FROM Material where materialId = ?1")
	fun queryByMaterialId(materialId: String): Material
}