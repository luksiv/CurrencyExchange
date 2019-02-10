package com.lusivic.currencyexchange.data.network

import com.google.gson.annotations.SerializedName

data class EVPApiResponse(
        @SerializedName("amount")
        val amount: String,
        @SerializedName("currency")
        val currency: String
)