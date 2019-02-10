package com.lusivic.currencyexchange.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.lusivic.currencyexchange.data.db.AppDatabase
import com.lusivic.currencyexchange.data.db.ExchangeHistory.ExchangeHistoryDao
import com.lusivic.currencyexchange.data.db.ExchangeHistory.ExchangeHistoryRepo
import com.lusivic.currencyexchange.data.db.MoneyBalance.MoneyBalanceDao
import com.lusivic.currencyexchange.data.db.MoneyBalance.MoneyBalanceRepo
import com.lusivic.currencyexchange.data.network.EVPApiService
import com.lusivic.currencyexchange.utils.AppConstants
import com.lusivic.currencyexchange.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): AppDatabase =
            Room
                    .databaseBuilder(context, AppDatabase::class.java, AppConstants.APP_DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()

    @Provides
    @Singleton
    internal fun provideMoneyBalanceDao(appDatabase: AppDatabase): MoneyBalanceDao = appDatabase.moneyBalanceDao()

    @Provides
    @Singleton
    internal fun provideMoneyBalanceRepo(moneyBalanceDao: MoneyBalanceDao): MoneyBalanceRepo = MoneyBalanceRepo(moneyBalanceDao)

    @Provides
    @Singleton
    internal fun provideExchangeHistoryDao(appDatabase: AppDatabase): ExchangeHistoryDao = appDatabase.exchangeHistoryDao()

    @Provides
    @Singleton
    internal fun provideExchangeHistoryRepo(exchangeHistoryDao: ExchangeHistoryDao): ExchangeHistoryRepo = ExchangeHistoryRepo(exchangeHistoryDao)

    @Provides
    @Singleton
    internal fun provideEVPApiService(): EVPApiService = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(AppConstants.EVP_API_BASE)
            .build()
            .create(EVPApiService::class.java)

    @Provides
    @Singleton
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()
}