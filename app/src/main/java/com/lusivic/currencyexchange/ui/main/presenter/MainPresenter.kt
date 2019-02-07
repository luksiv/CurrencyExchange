package com.lusivic.currencyexchange.ui.main.presenter

import com.lusivic.currencyexchange.ui.base.presenter.BasePresenter
import com.lusivic.currencyexchange.ui.main.view.IMainActivity
import com.lusivic.currencyexchange.utils.SchedulerProvider
import com.lusivic.currencyexchange.ui.main.interactor.IMainInteractor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter<V : IMainActivity, I : IMainInteractor> @Inject internal constructor(
    interactor: I,
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider
) : BasePresenter<V, I>(
    interactor = interactor,
    compositeDisposable = compositeDisposable,
    schedulerProvider = schedulerProvider
), IMainPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        getView()?.setUp()
    }


}