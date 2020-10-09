package com.ubitar.app.demo6.myxpopup

import android.os.Bundle
import android.view.LayoutInflater
import com.ubitar.app.BR
import com.ubitar.app.R
import com.ubitar.app.databinding.PopupLoadingBinding
import com.ubitar.app.demo6.xpopup.BaseCenterPopupView
import com.ubitar.capybara.mvvm.control.IController

class LoadingPopup private constructor(parent: IController) : BaseCenterPopupView<PopupLoadingBinding, LoadingPopupViewModel>(parent) {

    private lateinit var params: Params

    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int = R.layout.popup_loading

    override fun getViewModelId(): Int = BR.viewModel

    override fun initViewModelParams() {
        super.initViewModelParams()
        viewModel.params.value=params
    }

    class Params {
        var text = ""
    }

    class Builder {
        private val params = Params()

        fun setText(text: String): Builder {
            params.text = text
            return this
        }

        fun build(parent: IController): LoadingPopup {
            val dialog = LoadingPopup(parent)
            dialog.params = params
            return dialog
        }
    }

}