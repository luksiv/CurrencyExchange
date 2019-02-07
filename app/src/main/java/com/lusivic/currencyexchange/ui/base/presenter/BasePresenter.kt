package com.lusivic.currencyexchange.ui.base.presenter

import com.lusivic.currencyexchange.ui.base.view.IView
import com.lusivic.currencyexchange.utils.SchedulerProvider
import com.lusivic.currencyexchange.ui.base.interactor.IInteractor
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : IView, I : IInteractor> internal constructor(
    protected var interactor: I?,
    protected var compositeDisposable: CompositeDisposable,
    protected var schedulerProvider: SchedulerProvider
) : IPresenter<V, I> {

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun onDetach() {
        view = null
        interactor = null
    }

    override fun getView(): V? = view
}