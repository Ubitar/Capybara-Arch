package com.ubitar.app.launcher

import android.os.Bundle
import android.view.LayoutInflater

import com.blankj.utilcode.util.BarUtils
import com.ubitar.app.BR
import com.ubitar.app.R
import com.ubitar.app.common.IImmersionbar
import com.ubitar.app.databinding.ActivityMainBinding
import com.ubitar.capybara.mvvm.activity.BaseActivity


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), IImmersionbar {

    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int =
        R.layout.activity_main

    override fun getViewModelId(): Int = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSwipeBackEnable(false)
    }

    override fun initView() {
        super.initView()
        BarUtils.addMarginTopEqualStatusBarHeight(binding.viewStatusBar)
    }

}