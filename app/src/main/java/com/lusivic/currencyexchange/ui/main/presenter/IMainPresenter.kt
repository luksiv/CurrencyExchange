package com.lusivic.currencyexchange.ui.main.presenter

import com.lusivic.currencyexchange.ui.base.presenter.IPresenter
import com.lusivic.currencyexchange.ui.main.interactor.IMainInteractor
import com.lusivic.currencyexchange.ui.main.view.IMainActivity

interface IMainPresenter<V: IMainActivity, I: IMainInteractor>: IPresenter<V, I> {

    fun performExchange(amount: Double, currencyFrom: String, currencyTo: String)

}