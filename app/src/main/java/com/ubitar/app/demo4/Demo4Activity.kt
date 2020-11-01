package com.ubitar.app.demo4

import android.os.Bundle
import android.view.LayoutInflater

import com.blankj.utilcode.util.BarUtils
import com.ubitar.app.BR
import com.ubitar.app.R
import com.ubitar.app.common.IImmersionbar
import com.ubitar.app.databinding.ActivityDemo4Binding
import com.ubitar.capybara.mvvm.activity.BaseActivity


class Demo4Activity : BaseActivity<ActivityDemo4Binding, Demo4ViewModel>() , IImmersionbar {
    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int =
        R.layout.activity_demo4

    override fun getViewModelId(): Int = BR.viewModel


    override fun initView() {
        super.initView()
        BarUtils.addMarginTopEqualStatusBarHeight(binding.viewStatusBar)
    }

}