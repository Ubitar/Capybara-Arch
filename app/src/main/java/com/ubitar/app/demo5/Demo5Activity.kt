package com.ubitar.app.demo5

import android.os.Bundle
import android.view.LayoutInflater

import com.ubitar.app.BR
import com.ubitar.app.R
import com.ubitar.app.common.IImmersionbar
import com.ubitar.app.databinding.ActivityDemo5Binding
import com.ubitar.app.demo5.fragment.Demo5RootFragment
import com.ubitar.capybara.mvvm.activity.BaseActivity
import com.ubitar.capybara.mvvm.vm.impl.EmptyActivityViewModel


class Demo5Activity : BaseActivity<ActivityDemo5Binding, EmptyActivityViewModel>() , IImmersionbar {
    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int =
        R.layout.activity_demo5

    override fun getViewModelId(): Int = BR.viewModel

    override fun initView() {
        super.initView()
        loadRootFragment(R.id.layoutContainer, Demo5RootFragment())
    }
}