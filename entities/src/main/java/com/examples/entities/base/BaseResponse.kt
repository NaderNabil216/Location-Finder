package com.examples.entities.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


abstract class BaseResponse<T> {

    @SerializedName("meta")
    @Expose
    val meta = Meta()

    @SerializedName("response")
    @Expose
    val result: T? = null
}

data class Meta(
	@SerializedName("code")
	@Expose
	val code: Int = 0,

	@SerializedName("errorType")
	@Expose
	val errorType: String = "",

	@SerializedName("errorDetail")
	@Expose
	val errorDetail: String = "",

	@SerializedName("requestId")
	@Expose
	val requestId: String = ""

)