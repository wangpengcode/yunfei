package com.yunfei.finance.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import java.math.BigDecimal

@Configuration
@ConfigurationProperties(prefix = "yunfei.workload")
class WorkLoadRatio {
	lateinit var salaryRatio: String
	
	fun getSalaryRatio() : BigDecimal {
		return salaryRatio.toBigDecimalOrNull() ?: BigDecimal("1.5")
	}
}