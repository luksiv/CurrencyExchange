package com.lusivic.currencyexchange.data.db.MoneyBalance

import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class MoneyBalanceRepo @Inject internal constructor(private val moneyBalanceDao: MoneyBalanceDao) : IMoneyBalanceRepo {

    override fun isMoneyBalanceRepoEmpty(): Single<Boolean> = Single.fromCallable { moneyBalanceDao.getAllBalances().isEmpty() }

    override fun setUpInitialValues(): Completable =
            Completable.fromCallable {
                moneyBalanceDao.insertAll(listOf(
                        MoneyBalance(0, "EUR", 1000.00),
                        MoneyBalance(1, "USD", 0.00),
                        MoneyBalance(2, "JPY", 0.00)
                ))
            }

    override fun updateMoneyBalance(currency: String, newBalance: Double): Completable = Completable.fromCallable { moneyBalanceDao.update(currency, newBalance) }

    override fun updateMoneyBalance(newMoneyBalance: MoneyBalance): Completable = Completable.fromCallable { moneyBalanceDao.update(newMoneyBalance) }

    override fun getMoneyBalance(currency: String): Single<MoneyBalance> = Single.fromCallable { moneyBalanceDao.getMoneyBalance(currency) }

    override fun getAllMoneyBalances(): Single<List<MoneyBalance>> = Single.fromCallable { moneyBalanceDao.getAllBalances() }

    override fun registerExchange(convertedFrom: String, amountExchanged: Double, convertedTo: String, amountGotten: Double): Completable =
            Completable.fromCallable { moneyBalanceDao.updateExchange(convertedFrom, amountExchanged, convertedTo, amountGotten) }
}