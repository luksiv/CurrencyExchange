package com.lusivic.currencyexchange.di

import android.app.Application
import com.lusivic.currencyexchange.CurrencyExchangeApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: CurrencyExchangeApp)

}