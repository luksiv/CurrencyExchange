package com.lusivic.currencyexchange.data.db.MoneyBalance

import androidx.room.*

@Dao
interface MoneyBalanceDao {
    @Insert
    fun insert(moneyBalance: MoneyBalance)

    @Insert
    fun insertAll(moneyBalanceList: List<MoneyBalance>)

    @Update
    fun update(moneyBalance: MoneyBalance)

    @Query("UPDATE money_balance SET balance = :newBalance WHERE currency = :currency")
    fun update(currency: String, newBalance: Double)

    @Query("UPDATE money_balance SET balance = balance - :amount where currency = :currency")
    fun subtractFromBalance(currency: String, amount: Double)

    @Query("UPDATE money_balance SET balance = balance + :amount where currency = :currency")
    fun addToBalance(currency: String, amount: Double)

    @Transaction
    fun updateExchange(convertedFrom: String, amountExchanged: Double, convertedTo: String, amountGotten: Double){
        subtractFromBalance(convertedFrom, amountExchanged)
        addToBalance(convertedTo, amountGotten)
    }

    @Query("SELECT * FROM money_balance")
    fun getAllBalances(): List<MoneyBalance>

    @Query("SELECT * FROM money_balance WHERE currency = :currency")
    fun getMoneyBalance(currency: String): MoneyBalance
}