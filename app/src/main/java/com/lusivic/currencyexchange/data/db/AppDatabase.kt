package com.lusivic.currencyexchange.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lusivic.currencyexchange.data.db.ExchangeHistory.ExchangeHistory
import com.lusivic.currencyexchange.data.db.ExchangeHistory.ExchangeHistoryDao
import com.lusivic.currencyexchange.data.db.MoneyBalance.MoneyBalance
import com.lusivic.currencyexchange.data.db.MoneyBalance.MoneyBalanceDao

@Database(entities = [MoneyBalance::class, ExchangeHistory::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moneyBalanceDao(): MoneyBalanceDao
    abstract fun exchangeHistoryDao(): ExchangeHistoryDao
}