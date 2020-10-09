package com.ubitar.app.demo5.fragment

import android.os.Bundle
import android.view.LayoutInflater

import com.ubitar.app.BR
import com.ubitar.app.R
import com.ubitar.app.databinding.FragmentDemo5FirstBinding
import com.ubitar.app.demo5.vm.Demo5FirstViewModel
import com.ubitar.capybara.mvvm.fragment.BaseFragment

class Demo5FirstFragment : BaseFragment<FragmentDemo5FirstBinding, Demo5FirstViewModel>() {

    var position = 0

    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int =
        R.layout.fragment_demo5_first

    override fun getViewModelId(): Int = BR.viewModel

    override fun initParams() {
        super.initParams()
        position = arguments?.getInt("position", 0) ?: 0
    }

    override fun initView() {
        super.initView()
        viewModel.position.value = position
    }

    companion object {
        fun newInstance(position: Int): Demo5FirstFragment {
            val fragment = Demo5FirstFragment()
            val bundle = Bundle()
            bundle.putInt("position", position)
            fragment.arguments = bundle
            return fragment
        }
    }

}