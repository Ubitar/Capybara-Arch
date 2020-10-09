package com.ubitar.app.demo2.dialog

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.ubitar.app.BR
import com.ubitar.app.R
import com.ubitar.app.databinding.DialogDemo2Binding
import com.ubitar.app.demo2.Demo2Controllable
import com.ubitar.app.demo2.vm.Dem2DialogViewModel
import com.ubitar.capybara.mvvm.control.Control
import com.ubitar.capybara.mvvm.dialog.BaseDialogFragment

@Control(Demo2Controllable::class)
class Dem2Dialog : BaseDialogFragment<DialogDemo2Binding, Dem2DialogViewModel>() {

    private lateinit var listener: () -> Unit

    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int =
        R.layout.dialog_demo2

    override fun getViewModelId(): Int = BR.viewModel

    /** 设置可点击返回键关闭  */
    override fun getBackEnable(): Boolean = true

    /** 设置点击外部空白可关闭  */
    override fun getOutsideEnable(): Boolean = true

    /** 设置遮罩透明度  */
    override fun getDimAmount(): Float = 0.5f

    fun show(manager: FragmentManager) {
        super.show(manager, "demo2")
    }

    companion object {
        fun newInstance(listener: () -> Unit): Dem2Dialog {
            val fragment = Dem2Dialog()
            fragment.listener = listener
            return fragment
        }
    }

}