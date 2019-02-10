package com.lusivic.currencyexchange.ui.main.view

import com.lusivic.currencyexchange.data.db.MoneyBalance.MoneyBalance
import com.lusivic.currencyexchange.ui.base.view.IView

interface IMainActivity: IView {
    fun setUp()
    fun displayBalances(balances: List<MoneyBalance>)
    fun displayExchangeCount(exchangeCount: Int)
    fun displayFeeSum(currency: String, amount: Double)
    fun showDialog(title: String, message: String)
}