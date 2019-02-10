package com.lusivic.currencyexchange.ui.main.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.lusivic.currencyexchange.R
import com.lusivic.currencyexchange.data.db.MoneyBalance.MoneyBalance
import com.lusivic.currencyexchange.ui.base.view.BaseView
import com.lusivic.currencyexchange.ui.main.interactor.IMainInteractor
import com.lusivic.currencyexchange.ui.main.presenter.IMainPresenter
import com.lusivic.currencyexchange.utils.AppConstants
import com.lusivic.currencyexchange.utils.CommonUtils.roundTo
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseView(), IMainActivity {

    @Inject
    internal lateinit var mPresenter: IMainPresenter<IMainActivity, IMainInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter.onAttach(this)
    }

    override fun setUp() {
        Toast.makeText(this, "Injection works properly", Toast.LENGTH_SHORT).show()
        setUpCurrencySpinners()
        setUpOnClickListeners()
        mPresenter.updateData()
    }

    override fun displayBalances(balances: List<MoneyBalance>) {
        for (currency in balances) {
            val balance = roundTo(currency.balance, 3).toString()
            when (currency.currency) {
                "EUR" -> tv_eur_amount.text = balance
                "USD" -> tv_usd_amount.text = balance
                "JPY" -> tv_jpy_amount.text = balance
                else -> Log.e("MainActivity/displayBalances", "Unidentified MoneyBalance: $currency")
            }
        }
    }

    override fun displayExchangeCount(exchangeCount: Int) {
        tv_exchanges_amount.text = exchangeCount.toString()
    }

    override fun displayFeeSum(currency: String, amount: Double) {
        val roundedAmount = roundTo(amount, 3).toString()
        when (currency) {
            "EUR" -> tv_fee_amount_eur.text = roundedAmount
            "USD" -> tv_fee_amount_usd.text = roundedAmount
            "JPY" -> tv_fee_amount_jpy.text = roundedAmount
        }
    }

    override fun showDialog(title: String, message: String) {
        AlertDialog
                .Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton("Ok") { _, _ -> }
                .create()
                .show()
    }

    private fun setUpCurrencySpinners() {
        val spinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, AppConstants.AVAILABLE_CURRENCIES)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spn_currency_from.adapter = spinnerAdapter
        spn_currency_to.adapter = spinnerAdapter
    }

    private fun setUpOnClickListeners() {
        btn_exchange.setOnClickListener {
            if (isExchangeAmountEntered()) {
                if (areCurrenciesDifferent()) {
                    mPresenter.performExchange(
                            et_exchange_amount.text.toString().toDouble(),
                            spn_currency_from.selectedItem.toString(),
                            spn_currency_to.selectedItem.toString())
                } else {
                    Toast.makeText(this, "Currencies must be different!", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Amount can't be empty!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun isExchangeAmountEntered(): Boolean = et_exchange_amount.text.toString() != ""

    private fun areCurrenciesDifferent(): Boolean = spn_currency_from.selectedItemPosition != spn_currency_to.selectedItemPosition
}
