package com.yunfei.finance.persistence.repository

import com.yunfei.finance.persistence.entity.Staff
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface StaffRepository: CrudRepository<Staff, String> {
	@Query("FROM Staff where id = ?1")
	fun queryByStaffId(staffId: String): Staff
	
	@Query("FROM Staff where name = ?1")
	fun queryByName(name: String): List<Staff>
}