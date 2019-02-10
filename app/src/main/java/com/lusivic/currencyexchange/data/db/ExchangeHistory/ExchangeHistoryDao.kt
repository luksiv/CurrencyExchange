package com.lusivic.currencyexchange.data.db.ExchangeHistory

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExchangeHistoryDao {
    @Insert
    fun insert(exchangeHistory: ExchangeHistory)

    @Query("SELECT * FROM exchange_history ORDER BY exchange_timestamp DESC")
    fun selectAll(): List<ExchangeHistory>

    @Query("SELECT count(*) FROM EXCHANGE_HISTORY")
    fun getExchangeCount(): Int

    @Query("SELECT sum(applied_fee) FROM EXCHANGE_HISTORY WHERE currency_from = :currency")
    fun getSumAppliedFees(currency: String): Double
}