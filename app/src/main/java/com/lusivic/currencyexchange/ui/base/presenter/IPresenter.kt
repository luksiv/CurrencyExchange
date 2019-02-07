package com.lusivic.currencyexchange.ui.base.presenter

import com.lusivic.currencyexchange.ui.base.view.IView
import com.lusivic.currencyexchange.ui.base.interactor.IInteractor

interface IPresenter<V: IView, I: IInteractor> {
    fun onAttach(view: V?)
    fun onDetach()
    fun getView(): V?
}