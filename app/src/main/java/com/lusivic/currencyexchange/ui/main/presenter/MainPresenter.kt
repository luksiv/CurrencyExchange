package com.lusivic.currencyexchange.ui.main.presenter

import android.util.Log
import com.lusivic.currencyexchange.data.db.ExchangeHistory.ExchangeHistory
import com.lusivic.currencyexchange.ui.base.presenter.BasePresenter
import com.lusivic.currencyexchange.ui.main.interactor.IMainInteractor
import com.lusivic.currencyexchange.ui.main.view.IMainActivity
import com.lusivic.currencyexchange.utils.AppConstants
import com.lusivic.currencyexchange.utils.CommonUtils
import com.lusivic.currencyexchange.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class MainPresenter<V : IMainActivity, I : IMainInteractor> @Inject internal constructor(
        interactor: I,
        compositeDisposable: CompositeDisposable,
        scheduleProvider: SchedulerProvider
) : BasePresenter<V, I>(
        mInteractor = interactor,
        mCompositeDisposable = compositeDisposable,
        mSchedulerProvider = scheduleProvider
), IMainPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        getView()?.showProgress()
        mInteractor?.let {
            mCompositeDisposable.add(
                    it.isDatabaseEmpty()
                            .compose(mSchedulerProvider.ioToMainSingleScheduler())
                            .subscribe { result ->
                                if (result) setUpDatabase()
                                else getView()?.setUp()
                            }
            )
        }
    }

    override fun performExchange(amount: Double, currencyFrom: String, currencyTo: String) {
        getView()?.showProgress()
        Log.v("performExchange", "$amount $currencyFrom -> $currencyTo")
        if (isBalanceSufficient(currencyFrom, amount)) {
            val exchangeFee = CommonUtils.roundTo(calculateExchangeFee(amount, currencyFrom), 3)
            val adjustedAmount = amount - exchangeFee
            val exchangeValue = getExchangeValue(adjustedAmount, currencyFrom, currencyTo)
            mInteractor?.let {
                it.insertExchangeHistory(ExchangeHistory(null, Date().time, amount, currencyFrom, currencyTo, exchangeFee))
                        .subscribeOn(Schedulers.io())
                        .blockingAwait()
                it.updateBalances(currencyFrom, amount, currencyTo, exchangeValue)
                        .subscribeOn(Schedulers.io())
                        .blockingAwait()
            }
            getView()?.showDialog("Exchange successful", "Jūs konvertavote $amount $currencyFrom į $exchangeValue $currencyTo.\nKomisinis mokestis - $exchangeFee $currencyFrom.")
            updateData()
        } else {
            getView()?.let {
                it.hideProgress()
                it.showDialog("Exchange failed", "Nepakanka lėšų Jūsų $currencyFrom sąskaitoje")
            }
        }
    }

    private fun setUpDatabase() {
        getView()?.showProgress()
        mInteractor?.let {
            it.setUpDatabase()
                    .compose(mSchedulerProvider.ioToMainCompletableScheduler())
                    .subscribe {
                        getView()?.setUp()
                    }
        }
    }

    override fun updateData() {
        getView()?.showProgress()
        mInteractor?.let {
            mCompositeDisposable.addAll(
                    it.getBalances()
                            .compose(mSchedulerProvider.ioToMainSingleScheduler())
                            .subscribe({ balances ->
                                getView()?.let {
                                    it.displayBalances(balances)
                                    it.hideProgress()
                                }
                            }, { err ->
                                Log.e("MainPresenter/getBalances", err.message)
                                getView()?.hideProgress()
                            }),
                    it.getExchangeAmount()
                            .compose(mSchedulerProvider.ioToMainSingleScheduler())
                            .subscribe({ amount ->
                                getView()?.let {
                                    it.displayExchangeCount(amount)
                                }
                            }, { err -> Log.e("MainPresenter/getExchangeAmount", err.message) }),
                    it.getSumAppliedSum("EUR")
                            .compose(mSchedulerProvider.ioToMainSingleScheduler())
                            .subscribe({ amount ->
                                getView()?.displayFeeSum("EUR", amount)
                            }, { err -> Log.e("MainPresenter/getSumAppliedSum", err.message) }),
                    it.getSumAppliedSum("USD")
                            .compose(mSchedulerProvider.ioToMainSingleScheduler())
                            .subscribe({ amount ->
                                getView()?.displayFeeSum("USD", amount)
                            }, { err -> Log.e("MainPresenter/getSumAppliedSum", err.message) }),
                    it.getSumAppliedSum("JPY")
                            .compose(mSchedulerProvider.ioToMainSingleScheduler())
                            .subscribe({ amount ->
                                getView()?.displayFeeSum("JPY", amount)
                            }, { err -> Log.e("MainPresenter/getSumAppliedSum", err.message) })
            )
        }
    }

    // Exchange step 1
    private fun isBalanceSufficient(currency: String, amount: Double): Boolean {
        mInteractor?.let {
            return amount <= it.getBalance(currency)
                    .subscribeOn(Schedulers.io())
                    .blockingGet().balance
        }
        return false
    }

    // Exchange step 2
    private fun calculateExchangeFee(amount: Double, currencyFrom: String): Double = when (isExchangeFree()) {
        true -> 0.0
        else -> {
            when (currencyFrom) {
                "EUR" -> amount * AppConstants.EUR_EXCHANGE_FEE
                "USD" -> amount * AppConstants.USD_EXCHANGE_FEE
                "JPY" -> amount * AppConstants.JPY_EXCHANGE_FEE
                else -> throw IllegalArgumentException("Unidentified currency")
            }
        }
    }

    private fun isExchangeFree(): Boolean {
        mInteractor?.let {
            return AppConstants.FREE_EXCHANGES > it.getExchangeAmount().subscribeOn(Schedulers.io()).blockingGet()
        }
        return false
    }

    // Exchange step 3
    private fun getExchangeValue(amount: Double, currencyFrom: String, currencyTo: String): Double {
        mInteractor?.let {
            return it.getExchangeValue(amount, currencyFrom, currencyTo)
                    .subscribeOn(Schedulers.io())
                    .blockingGet().amount.toDouble()
        }
        return -1.0
    }
}