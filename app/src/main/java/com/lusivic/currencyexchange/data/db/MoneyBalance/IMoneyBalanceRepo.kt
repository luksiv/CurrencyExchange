package com.lusivic.currencyexchange.data.db.MoneyBalance

import io.reactivex.Completable
import io.reactivex.Single

interface IMoneyBalanceRepo {
    fun isMoneyBalanceRepoEmpty(): Single<Boolean>
    fun setUpInitialValues(): Completable
    fun updateMoneyBalance(currency: String, newBalance: Double): Completable
    fun updateMoneyBalance(newMoneyBalance: MoneyBalance): Completable
    fun getMoneyBalance(currency: String): Single<MoneyBalance>
    fun getAllMoneyBalances(): Single<List<MoneyBalance>>
    fun registerExchange(convertedFrom: String, amountExchanged: Double, convertedTo: String, amountGotten: Double): Completable
}