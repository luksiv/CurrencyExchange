package com.lusivic.currencyexchange.ui.main

import com.lusivic.currencyexchange.ui.main.interactor.IMainInteractor
import com.lusivic.currencyexchange.ui.main.interactor.MainInteractor
import com.lusivic.currencyexchange.ui.main.presenter.IMainPresenter
import com.lusivic.currencyexchange.ui.main.presenter.MainPresenter
import com.lusivic.currencyexchange.ui.main.view.IMainActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainInteractor(mainInteractor: MainInteractor): IMainInteractor = mainInteractor

    @Provides
    internal fun provideMainPresenter(mainPresenter: MainPresenter<IMainActivity, IMainInteractor>):
            IMainPresenter<IMainActivity, IMainInteractor> = mainPresenter
}