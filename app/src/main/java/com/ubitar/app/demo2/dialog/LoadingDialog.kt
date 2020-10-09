package com.ubitar.app.demo2.dialog

import android.os.Bundle
import android.view.LayoutInflater
import com.ubitar.app.R
import com.ubitar.app.databinding.DialogLoadingBinding
import com.ubitar.capybara.mvvm.dialog.BaseDialogFragment
import com.ubitar.capybara.mvvm.vm.EmptyDialogViewModel

class LoadingDialog : BaseDialogFragment<DialogLoadingBinding, EmptyDialogViewModel>() {

    private lateinit var params: Params

    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int =
        R.layout.dialog_loading

    override fun getViewModelId(): Int = 0

    override fun getDimAmount(): Float = params.dimAmount
    override fun isDimAmountEnable(): Boolean = params.dimAmountEnable
    override fun getOutsideEnable(): Boolean = params.outsideEnable
    override fun getBackEnable(): Boolean = params.backEnable

    class Params {
        var dimAmount = 0.5f
        var dimAmountEnable = true
        var outsideEnable = false
        var backEnable = true
    }

    class Builder {
        private val params = Params()
        private var listener: (() -> Unit)? = null

        fun setOutsideCancelable(cancelable: Boolean): Builder {
            params.outsideEnable = cancelable
            return this
        }

        fun setBackEnable(enable: Boolean): Builder {
            params.backEnable = enable
            return this
        }

        fun setDimAmount(dimAmount: Float): Builder {
            params.dimAmount = dimAmount
            return this
        }

        fun setDimAmountEnable(enable: Boolean): Builder {
            params.dimAmountEnable = enable
            return this
        }

        fun setDismissListener(listener: () -> Unit): Builder {
            this.listener = listener
            return this
        }


        fun build(): LoadingDialog {
            val dialog = LoadingDialog()
            dialog.params = params
            dialog.setOnCanceledListener(listener)
            return dialog
        }
    }

}