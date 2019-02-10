package com.lusivic.currencyexchange.ui.main.interactor

import com.lusivic.currencyexchange.data.db.ExchangeHistory.ExchangeHistory
import com.lusivic.currencyexchange.data.db.ExchangeHistory.ExchangeHistoryRepo
import com.lusivic.currencyexchange.data.db.MoneyBalance.MoneyBalance
import com.lusivic.currencyexchange.data.db.MoneyBalance.MoneyBalanceRepo
import com.lusivic.currencyexchange.data.network.EVPApiResponse
import com.lusivic.currencyexchange.data.network.EVPApiService
import com.lusivic.currencyexchange.ui.base.interactor.BaseInteractor
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class MainInteractor @Inject internal constructor(private val mMoneyBalanceRepo: MoneyBalanceRepo, private val mExchangeHistoryRepo: ExchangeHistoryRepo, private val mEVPApiService: EVPApiService) : BaseInteractor(), IMainInteractor {
    override fun isDatabaseEmpty(): Single<Boolean> = mMoneyBalanceRepo.isMoneyBalanceRepoEmpty()
    override fun setUpDatabase(): Completable = mMoneyBalanceRepo.setUpInitialValues()
    override fun getBalances(): Single<List<MoneyBalance>> = mMoneyBalanceRepo.getAllMoneyBalances()
    override fun getExchangeAmount(): Single<Int> = mExchangeHistoryRepo.getExchangeCount()
    override fun getBalance(currency: String): Single<MoneyBalance> = mMoneyBalanceRepo.getMoneyBalance(currency)
    override fun getExchangeValue(amount: Double, currencyFrom: String, currencyTo: String): Single<EVPApiResponse> = mEVPApiService.getExchangeValue(amount, currencyFrom, currencyTo)
    override fun getSumAppliedSum(currency: String): Single<Double> = mExchangeHistoryRepo.getSumAppliedFees(currency)
    override fun insertExchangeHistory(exchangeHistory: ExchangeHistory): Completable = mExchangeHistoryRepo.insertExchangeHistory(exchangeHistory)
    override fun updateBalances(convertedFrom: String, amountExchanged: Double, convertedTo: String, amountGotten: Double): Completable =
            mMoneyBalanceRepo.registerExchange(convertedFrom, amountExchanged, convertedTo, amountGotten)
}