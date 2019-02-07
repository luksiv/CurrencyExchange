package com.lusivic.currencyexchange.ui.main.presenter

import com.lusivic.currencyexchange.ui.base.presenter.IPresenter
import com.lusivic.currencyexchange.ui.main.view.IMainActivity
import com.lusivic.currencyexchange.ui.main.interactor.IMainInteractor

interface IMainPresenter<V: IMainActivity, I: IMainInteractor>: IPresenter<V, I> {

}