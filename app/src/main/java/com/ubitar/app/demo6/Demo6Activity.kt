package com.ubitar.app.demo6

import android.os.Bundle
import android.view.LayoutInflater

import com.blankj.utilcode.util.BarUtils
import com.ubitar.app.BR
import com.ubitar.app.R
import com.ubitar.app.databinding.ActivityDemo6Binding
import com.ubitar.app.demo6.vm.Demo6ViewModel
import com.ubitar.capybara.mvvm.activity.BaseActivity
import com.ubitar.capybara.mvvm.control.Control


@Control(Demo6Controllable::class)
class Demo6Activity: BaseActivity<ActivityDemo6Binding, Demo6ViewModel>() {
    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int = R.layout.activity_demo6

    override fun getViewModelId(): Int =BR.viewModel

    override fun initView() {
        super.initView()
        BarUtils.addMarginTopEqualStatusBarHeight(binding.viewStatusBar)
    }
}