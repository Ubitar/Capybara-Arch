package com.ubitar.capybara.mvvm.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer

import com.ubitar.capybara.mvvm.R
import com.ubitar.capybara.mvvm.control.impl.ControllableProvider


import com.ubitar.capybara.mvvm.vm.BaseFragmentViewModel
import com.weikaiyun.fragmentation.ExtraTransaction

/**
 * Created by laohuang on 2018/9/9.
 */

abstract class BaseFragment<V : ViewDataBinding, VM : BaseFragmentViewModel<*>> :
    BaseMvvMFragment<V, VM>() {

    protected lateinit var controllableProvider: ControllableProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        return attachToSwipeBack(view)
    }

    override fun onCreatedViewModel() {
        super.onCreatedViewModel()
        initDaggerInject()
    }

    override fun onBeforeObservable() {
        super.onBeforeObservable()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controllableProvider = ControllableProvider.with(this)
        controllableProvider.get().onCreate()
        initViewModelParams()
        initView()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onVisible() {
        super.onVisible()
    }

    override fun onInvisible() {
        super.onInvisible()
    }

    override fun onDestroyView() {
        controllableProvider.get().onDestroy()
        super.onDestroyView()
    }

    override fun lazyInit() {
        super.lazyInit()
        viewModel.initEvent(this)
        viewModel.initData()
    }

    open fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return false
    }

    open fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return false
    }

    override fun extraTransaction(): ExtraTransaction {
        val transaction = super.extraTransaction()
        val enterAnim = arguments?.getInt("custom_enter_transition_animation", R.anim.h_fragment_enter) ?: R.anim.h_fragment_enter
        val exitAnim = arguments?.getInt("custom_exit_transition_animation", R.anim.h_fragment_exit) ?: R.anim.h_fragment_exit
        val popEnterAnim = arguments?.getInt("custom_pop_enter_transition_animation", R.anim.h_fragment_pop_enter) ?: R.anim.h_fragment_pop_enter
        val popExitAnim = arguments?.getInt("custom_pop_exit_transition_animation", R.anim.h_fragment_pop_exit) ?: R.anim.h_fragment_pop_exit
        transaction.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim)
        return transaction
    }

    override fun onBindObservable() {
        super.onBindObservable()
        viewModel.getBaseActions().hideKeyboardAction.observe(viewLifecycleOwner, Observer {
            hideSoftInput()
        })
    }

    override fun showLoading(isOutsideEnable: Boolean, isBackEnable: Boolean, onCanceledListener: (() -> Unit)?, extra: Array<out Any?>) {
        controllableProvider.get().showLoading( isOutsideEnable, isBackEnable, onCanceledListener, extra)
    }

    override fun showSuccess(text: String, onDismissListener: (() -> Unit)?, extra: Array<out Any?>) {
        controllableProvider.get().showSuccess(text, onDismissListener, extra)
    }

    override fun showFail(text: String, onDismissListener: (() -> Unit)?, extra: Array<out Any?>) {
        controllableProvider.get().showFail(text, onDismissListener, extra)
    }

    override fun hideLoading() {
        controllableProvider.get().hideLoading()
    }

    override fun showMessage(text: String, onDismissListener: (() -> Unit)?, extra: Array<out Any?>) {
        controllableProvider.get().showMessage(text, onDismissListener, extra)
    }

    /** 初始化页面参数  */
    open fun initParams() {

    }

    /** Dagger注入 */
    open fun initDaggerInject() {

    }

    /** 初始化或传递ViewModel的参数  */
    open fun initViewModelParams() {

    }

    /** 初始化视图  */
    open fun initView() {

    }


}
