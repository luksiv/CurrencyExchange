package com.lusivic.currencyexchange.data.db.ExchangeHistory

import io.reactivex.Completable
import io.reactivex.Single

interface IExchangeHistoryRepo {
    fun insertExchangeHistory(exchangeHistory: ExchangeHistory): Completable
    fun getAllHistory(): Single<List<ExchangeHistory>>
    fun getExchangeCount(): Single<Int>
    fun getSumAppliedFees(currency: String): Single<Double>
}