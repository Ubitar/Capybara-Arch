package com.ubitar.app.demo5.fragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.blankj.utilcode.util.BarUtils
import com.ubitar.app.BR
import com.ubitar.app.R
import com.ubitar.app.databinding.FragmentDemo5RootBinding
import com.ubitar.app.demo5.vm.Demo5RootViewModel
import com.ubitar.capybara.mvvm.fragment.BaseFragment


class Demo5RootFragment : BaseFragment<FragmentDemo5RootBinding, Demo5RootViewModel>() {
    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int =
        R.layout.fragment_demo5_root

    override fun getViewModelId(): Int = BR.viewModel

    override fun initView() {
        super.initView()
        BarUtils.addMarginTopEqualStatusBarHeight(binding.viewStatusBar)
        initViewPager2()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onVisible() {
        super.onVisible()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyUp(keyCode, event)
    }

    /** 初始化ViewPager */
    private fun initViewPager2() {
        binding.viewPager.offscreenPageLimit = 1
        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 5

            override fun createFragment(position: Int): Fragment {
                return Demo5FirstFragment.newInstance(position)
            }
        }
    }

}