package com.yunfei.finance.service.salary

import com.yunfei.finance.config.WorkLoadRatio
import com.yunfei.finance.dto.SalaryUploadDto
import com.yunfei.finance.persistence.entity.Salary
import com.yunfei.finance.service.persistence.MaterialPersistenceService
import com.yunfei.finance.service.persistence.SalaryPersistenceService
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


@Service
class SalaryProcessingService(
	val salaryPersistenceService: SalaryPersistenceService,
	val materialPersistenceService: MaterialPersistenceService,
	val workLoadRatio: WorkLoadRatio
) {
	fun salaryProcessing(dto: SalaryUploadDto): Salary {
		return try {
			val fmt: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			val salaryRatio =
				if (dto.workOvertime == true || dto.isWorkLoad()) workLoadRatio.getSalaryRatio() else BigDecimal.ONE
			val material = materialPersistenceService.findById(dto.materialId) ?: throw Exception("未知的简易编号")
			val salaryAmount =
				salaryRatio.multiply(BigDecimal(dto.quantity)).multiply(material.unitPrice.toString().toBigDecimal())
			var timeSeconds = BigDecimal(fmt.parse(dto.end).time).subtract(BigDecimal(fmt.parse(dto.start).time)).divide(BigDecimal("3600000"))
			if (dto.launchDeduct == true) {
				timeSeconds = timeSeconds.subtract(BigDecimal("0.5"))
			}
			val salary = Salary(
				materialId = material.materialId,
				staffId = dto.staffId,
				salaryDate = dto.salaryDate,
				start = dto.start,
				end = dto.end,
				launchDeduct = dto.launchDeduct ?: false,
				quantity = dto.quantity,
				subsidy = dto.subsidy,
				workOvertime = dto.workOvertime ?: dto.isWorkLoad(),
				salarySummary = salaryAmount,
				workTime = timeSeconds
			)
			return salaryPersistenceService.save(salary)
		} catch (e: Exception) {
			if (e is DataIntegrityViolationException) {
				throw Exception("重复输入!")
			} else {
				throw e
			}
		}
	}
}