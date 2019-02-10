package com.lusivic.currencyexchange.data.db.ExchangeHistory

import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ExchangeHistoryRepo @Inject internal constructor(private val exchangeHistoryDao: ExchangeHistoryDao): IExchangeHistoryRepo{
    override fun insertExchangeHistory(exchangeHistory: ExchangeHistory): Completable = Completable.fromCallable { exchangeHistoryDao.insert(exchangeHistory) }

    override fun getAllHistory(): Single<List<ExchangeHistory>> = Single.fromCallable { exchangeHistoryDao.selectAll() }

    override fun getExchangeCount(): Single<Int> = Single.fromCallable { exchangeHistoryDao.getExchangeCount() }

    override fun getSumAppliedFees(currency: String): Single<Double> = Single.fromCallable { exchangeHistoryDao.getSumAppliedFees(currency) }
}