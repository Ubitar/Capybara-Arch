package com.ubitar.app.demo1

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer

import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.ubitar.app.BR
import com.ubitar.app.R
import com.ubitar.app.common.IImmersionbar
import com.ubitar.app.databinding.ActivityDemo1Binding
import com.ubitar.capybara.mvvm.activity.BaseActivity


class Demo1Activity : BaseActivity<ActivityDemo1Binding, Demo1ViewModel>(),IImmersionbar {

    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int =
        R.layout.activity_demo1

    override fun getViewModelId(): Int = BR.viewModel


    /**
     * 下方函数运行顺序（从上往下顺序运行）
     *
     *  initParams()
     *  onCreatedViewModel()  /   initDaggerInject()
     *  onBeforeObservable()
     *  onBindObservable()
     *  initViewModelParams()
     *  initView()
     *  ViewModel.initEvent()
     *  ViewModel.initData()
     *
     */
    override fun initParams() {
        super.initParams()
        //用于接收并处理从上一个界面传递过来的数据
    }

    override fun onCreatedViewModel() {
        super.onCreatedViewModel()
    }

    //这里可以进行Dagger注入
    override fun initDaggerInject() {
        super.initDaggerInject()
    }

    override fun onBeforeObservable() {
        super.onBeforeObservable()
    }

    override fun onBindObservable() {
        super.onBindObservable()
        viewModel.actions.customAction.observe(this, Observer {
            ToastUtils.showShort("Activity收到了来自ViewModel的信息 $it")
        })
    }

    override fun initViewModelParams() {
        super.initViewModelParams()
        //如果有需要，可以通过这个函数把初始数据传到ViewModel
        viewModel.valueFromActivity = "注入的值"
    }

    override fun initView() {
        super.initView()
        BarUtils.addMarginTopEqualStatusBarHeight(binding.viewStatusBar)
    }

}
