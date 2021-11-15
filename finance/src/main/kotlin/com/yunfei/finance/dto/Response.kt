package com.yunfei.finance.dto

data class Response<T> (
	val isSucceed: Boolean,
	val message: String? = null,
	val data: T? = null
)