package com.ubitar.app.demo2

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer

import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.ubitar.app.BR
import com.ubitar.app.R
import com.ubitar.app.databinding.ActivityDemo2Binding
import com.ubitar.app.demo2.dialog.Dem2Dialog
import com.ubitar.app.demo2.vm.Demo2ViewModel
import com.ubitar.capybara.mvvm.activity.BaseActivity
import com.ubitar.capybara.mvvm.control.Control


@Control(type = Demo2Controllable::class) //用来设置BaseActivity中showLoading和showMessage的行为
class Demo2Activity : BaseActivity<ActivityDemo2Binding, Demo2ViewModel>() {

    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int =
        R.layout.activity_demo2

    override fun getViewModelId(): Int = BR.viewModel

    override fun initView() {
        super.initView()
        BarUtils.addMarginTopEqualStatusBarHeight(binding.viewStatusBar)

        /** 也可以在你的APP中设置一个全局的 Controllable，这样就不用在每个Activity或fragment中都添加Control注解 */
//        ControlProvider.setGlobalConfig(ControlConfig().setGlobalControl(Demo2Controllable()))
    }

    override fun onBindObservable() {
        super.onBindObservable()
        viewModel.showCustomDialogAction.observe(this, Observer {
            showCustomDialog()
        })
    }

    private fun showCustomDialog() {
        Dem2Dialog.newInstance {
            ToastUtils.showShort("自定义弹窗已关闭")
        }.show(supportFragmentManager)
    }
}