package com.lusivic.currencyexchange.ui.base.presenter

import com.lusivic.currencyexchange.ui.base.interactor.IInteractor
import com.lusivic.currencyexchange.ui.base.view.IView
import com.lusivic.currencyexchange.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : IView, I : IInteractor> internal constructor(
        protected var mInteractor: I?,
        protected var mCompositeDisposable: CompositeDisposable,
        internal var mSchedulerProvider: SchedulerProvider
) : IPresenter<V, I> {

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun onDetach() {
        view = null
        mInteractor = null
    }

    override fun getView(): V? = view
}