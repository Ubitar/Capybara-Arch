package com.ubitar.app.demo3

import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager

import com.blankj.utilcode.util.BarUtils
import com.ubitar.app.BR
import com.ubitar.app.R
import com.ubitar.app.common.IImmersionbar
import com.ubitar.app.databinding.ActivityDemo3Binding
import com.ubitar.app.demo3.vm.Demo3ViewModel

import com.ubitar.capybara.mvvm.activity.BaseActivity

@IImmersionbar
class Demo3Activity : BaseActivity<ActivityDemo3Binding, Demo3ViewModel>() {

    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int =
        R.layout.activity_demo3

    override fun getViewModelId(): Int = BR.viewModel

    override fun initView() {
        super.initView()
        BarUtils.addMarginTopEqualStatusBarHeight(binding.viewStatusBar)
        initRecyclerView()
    }

    /** 初始化RecyclerView */
    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

}