package com.lusivic.currencyexchange.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface EVPApiService {
    @GET("{amount}-{currencyFrom}/{currencyTo}/latest")
    fun getExchangeValue(@Path("amount") amount: Double,
                         @Path("currencyFrom") currencyFrom: String,
                         @Path("currencyTo") currencyTo: String): Single<EVPApiResponse>
}