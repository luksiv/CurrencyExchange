package com.lusivic.currencyexchange.utils

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import androidx.core.graphics.drawable.toDrawable
import com.lusivic.currencyexchange.R
import kotlin.math.round

object CommonUtils {
    fun showLoadingDialog(context: Context?): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.let {
            it.show()
            it.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            it.setContentView(R.layout.progress_dialog)
            it.isIndeterminate = true
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            return it
        }
    }

    fun roundTo(number: Double, decimalPlaces: Int) = round(number * Math.pow(10.0, decimalPlaces.toDouble())) / Math.pow(10.0, decimalPlaces.toDouble())
}