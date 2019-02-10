package com.lusivic.currencyexchange.ui.main.interactor

import com.lusivic.currencyexchange.data.db.ExchangeHistory.ExchangeHistory
import com.lusivic.currencyexchange.data.db.MoneyBalance.MoneyBalance
import com.lusivic.currencyexchange.data.network.EVPApiResponse
import com.lusivic.currencyexchange.ui.base.interactor.IInteractor
import io.reactivex.Completable
import io.reactivex.Single

interface IMainInteractor : IInteractor {

    fun isDatabaseEmpty(): Single<Boolean>
    fun setUpDatabase(): Completable
    fun getBalances(): Single<List<MoneyBalance>>
    fun getExchangeAmount(): Single<Int>
    fun getSumAppliedSum(currency: String): Single<Double>
    fun getBalance(currency: String): Single<MoneyBalance>
    fun getExchangeValue(amount: Double, currencyFrom: String, currencyTo: String): Single<EVPApiResponse>
    fun insertExchangeHistory(exchangeHistory: ExchangeHistory): Completable
    fun updateBalances(convertedFrom: String, amountExchanged: Double, convertedTo: String, amountGotten: Double): Completable
}