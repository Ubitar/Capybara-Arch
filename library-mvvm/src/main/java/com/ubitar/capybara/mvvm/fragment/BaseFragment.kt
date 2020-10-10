package com.ubitar.capybara.mvvm.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer

import com.gyf.immersionbar.ImmersionBar
import com.ubitar.capybara.mvvm.R
import com.ubitar.capybara.mvvm.control.ControlProvider


import com.ubitar.capybara.mvvm.vm.base.BaseFragmentViewModel
import me.yokeyword.fragmentation.anim.FragmentAnimator

/**
 * Created by laohuang on 2018/9/9.
 */

abstract class BaseFragment<V : ViewDataBinding, VM : BaseFragmentViewModel<*>> :
    BaseMvvMFragment<V, VM>() {

    protected lateinit var controllerProvider: ControlProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controllerProvider = ControlProvider(this)
        initParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        return attachToSwipeBack(view!!)
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
        initViewModelParams()
        initView()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        getImmersionBar()?.init()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onSupportVisible() {
        super.onSupportVisible()
        getImmersionBar()?.init()
    }

    override fun onSupportInvisible() {
        super.onSupportInvisible()
    }

    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        super.onEnterAnimationEnd(savedInstanceState)
        if (isInitDataAfterAnimation() && viewModel.isUnInitData) {
            viewModel.initData()
        }
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        viewModel.initEvent(this)
        if (!isInitDataAfterAnimation()) {
            viewModel.initData()
        }
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        val enterAnim = arguments?.getInt("custom_enter_transition_animation", R.anim.h_fragment_enter) ?: R.anim.h_fragment_enter
        val exitAnim = arguments?.getInt("custom_exit_transition_animation", R.anim.h_fragment_exit) ?: R.anim.h_fragment_exit
        val popEnterAnim = arguments?.getInt("custom_pop_enter_transition_animation", R.anim.h_fragment_pop_enter) ?: R.anim.h_fragment_pop_enter
        val popExitAnim = arguments?.getInt("custom_pop_exit_transition_animation", R.anim.h_fragment_pop_exit) ?: R.anim.h_fragment_pop_exit
        return FragmentAnimator(enterAnim, exitAnim, popEnterAnim, popExitAnim)
    }


    override fun onDestroyController() {
        controllerProvider.get().onDestroy()
    }

    override fun onBindObservable() {
        super.onBindObservable()
        viewModel.getBaseActions().hideKeyboardAction.observe(viewLifecycleOwner, Observer {
            hideSoftInput()
        })
    }

    override fun showLoading(isOutsideEnable: Boolean, isBackEnable: Boolean, onCanceledListener: (() -> Unit)?,  extra: Array<out Any?>) {
        controllerProvider.get().showLoading(this, isOutsideEnable, isBackEnable, onCanceledListener, extra)
    }

    override fun showSuccess(text: String,  extra: Array<out Any?>) {
        controllerProvider.get().showSuccess(text, extra)
    }

    override fun showFail(text: String,  extra: Array<out Any?>) {
        controllerProvider.get().showFail(text, extra)
    }

    override fun hideLoading() {
        controllerProvider.get().hideLoading()
    }

    override fun showMessage(text: String,  extra: Array<out Any?>) {
        controllerProvider.get().showMessage(text, extra)
    }

    /** 设置状态栏效果  */
    open fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .keyboardEnable(true, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            .navigationBarDarkIcon(true)
            .navigationBarColor(android.R.color.white)
    }

    /** 是否再加载完动画后才开始加载数据，这样是为了防止卡顿 ，默认：加载动画的同时调用 initData() */
    open fun isInitDataAfterAnimation(): Boolean {
        return false
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
