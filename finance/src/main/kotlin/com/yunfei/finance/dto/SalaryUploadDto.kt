package com.yunfei.finance.dto

import java.math.BigDecimal
import java.sql.Date
import java.text.DateFormat
import java.text.SimpleDateFormat


data class SalaryUploadDto(
	val materialId: String,
	val salaryDate: String,
	val start: String,
	val end: String,
	var launchDeduct: Boolean? = null,
	val quantity: Int,
	var workOvertime: Boolean? = null,
	val subsidy: BigDecimal,
	val staffId: String
) {
	fun isWorkLoad(): Boolean {
		val fmt: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		val standardStr = this.salaryDate+" 16:00:00"
		val standard = fmt.parse(standardStr)
		val startTime = fmt.parse(this.start)
		return startTime.after(standard)
	}
}