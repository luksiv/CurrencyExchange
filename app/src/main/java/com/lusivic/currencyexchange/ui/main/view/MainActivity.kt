package com.lusivic.currencyexchange.ui.main.view

import android.os.Bundle
import android.widget.Toast
import com.lusivic.currencyexchange.R
import com.lusivic.currencyexchange.ui.base.view.BaseView
import com.lusivic.currencyexchange.ui.main.interactor.IMainInteractor
import com.lusivic.currencyexchange.ui.main.presenter.IMainPresenter
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
    }
}
