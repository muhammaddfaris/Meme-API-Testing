package com.dapoi.memeapp.data

import com.squareup.moshi.Json


/**
 *
 * TODO: Membuat data class berdasarkan api
 */
data class MemeResponse(

	@Json(name="data")
	val data: List<DataItem>,
)

data class DataItem(

	@Json(name="source")
	val source: String,

	@Json(name="url")
	val url: String
)
