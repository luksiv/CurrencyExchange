package com.lusivic.currencyexchange.data.db.MoneyBalance

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "money_balance")
data class MoneyBalance(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: Int,
        @ColumnInfo(name = "currency")
        val currency: String,
        @ColumnInfo(name = "balance")
        val balance: Double
)