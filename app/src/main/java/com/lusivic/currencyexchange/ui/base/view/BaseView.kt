package com.lusivic.currencyexchange.ui.base.view

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import com.lusivic.currencyexchange.utils.CommonUtils
import dagger.android.AndroidInjection

abstract class BaseView: Activity(), IView {
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }

    override fun showProgress() {
        hideProgress()
        progressDialog = CommonUtils.showLoadingDialog(this)
    }

    override fun hideProgress() {
        progressDialog?.let { if(it.isShowing) it.cancel() }
    }
}