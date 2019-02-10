package com.lusivic.currencyexchange.data.db.ExchangeHistory

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_history")
data class ExchangeHistory(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: Int?,
        @ColumnInfo(name = "exchange_timestamp")
        val timestamp: Long,
        @ColumnInfo(name = "amount")
        val amount: Double,
        @ColumnInfo(name = "currency_from")
        val currencyFrom: String,
        @ColumnInfo(name = "currency_to")
        val currencyTo: String,
        @ColumnInfo(name = "applied_fee")
        val appliedFee: Double
)