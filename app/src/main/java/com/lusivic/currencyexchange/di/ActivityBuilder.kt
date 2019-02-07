package com.lusivic.currencyexchange.di

import com.lusivic.currencyexchange.ui.main.MainActivityModule
import com.lusivic.currencyexchange.ui.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}