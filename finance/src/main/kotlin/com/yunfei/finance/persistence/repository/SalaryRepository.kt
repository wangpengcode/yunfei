package com.yunfei.finance.persistence.repository

import com.yunfei.finance.persistence.entity.Salary
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SalaryRepository: CrudRepository<Salary, String> {
	@Query("FROM Salary where staffId = ?1 and salaryDate = ?2")
	fun queryByStaffIdAndDay(staffId: String,day: String): List<Salary>
}