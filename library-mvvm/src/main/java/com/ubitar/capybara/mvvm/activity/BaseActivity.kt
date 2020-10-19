package com.ubitar.capybara.mvvm.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

import com.gyf.immersionbar.ImmersionBar
import com.ubitar.capybara.mvvm.common.ActivityManager
import com.ubitar.capybara.mvvm.control.ControlProvider
import com.ubitar.capybara.mvvm.vm.base.BaseActivityViewModel
import com.ubitar.capybara.mvvm.R

abstract class BaseActivity<V : ViewDataBinding, VM : BaseActivityViewModel<*>> :
    BaseMvvMActivity<V, VM>() {

    protected lateinit var controllerProvider: ControlProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityManager.getManager().addActivity(this)
        getImmersionBar()?.init()
        controllerProvider = ControlProvider(this)
        initParams()
        initViewModelParams()
        initView()
        viewModel.initEvent(this)
        viewModel.initData()
    }

    override fun onCreatedViewModel() {
        super.onCreatedViewModel()
        initDaggerInject()
    }

    override fun onBeforeObservable() {
        super.onBeforeObservable()
    }

    override fun onDestroyController() {
        controllerProvider.get().onDestroy()
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.getManager().finishActivity(this)
    }

    override fun finish() {
        super.finish()
        val enterAnim = intent.getIntExtra(CUSTOM_POP_ENTER_TRANSITION_ANIMATION_TAG, R.anim.h_fragment_pop_enter)
        val exitAnim = intent.getIntExtra(CUSTOM_EXIT_TRANSITION_ANIMATION_TAG, R.anim.h_fragment_exit)
        overridePendingTransition(enterAnim, exitAnim)
    }

    //使onActivityResult能够传到fragment
    @SuppressLint("RestrictedApi")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        onActivityResultForFragment(supportFragmentManager.fragments, requestCode, resultCode, data)
    }

    @SuppressLint("RestrictedApi")
    private fun onActivityResultForFragment(
        rootFragmentList: List<Fragment>?,
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (rootFragmentList != null) {
            for (fragment in rootFragmentList) {
                if (fragment == null) continue
                fragment.onActivityResult(requestCode, resultCode, data)
                onActivityResultForFragment(
                    fragment.childFragmentManager.fragments,
                    requestCode,
                    resultCode,
                    data
                )
            }
        }
    }

    override fun showLoading(isOutsideEnable: Boolean, isBackEnable: Boolean, onCanceledListener: (() -> Unit)?,   extra: Array<out Any?>) {
        controllerProvider.get().showLoading(this, isOutsideEnable, isBackEnable, onCanceledListener, extra)
    }

    override fun showSuccess(text: String,   extra: Array<out Any?>) {
        controllerProvider.get().showSuccess(text,  extra)
    }

    override fun showFail(text: String,  extra: Array<out Any?>) {
        controllerProvider.get().showFail(text,  extra)
    }

    override fun hideLoading() {
        controllerProvider.get().hideLoading()
    }

    override fun showMessage(text: String,  extra: Array<out Any?>) {
        controllerProvider.get().showMessage(text, extra)
    }

    override fun getContext(): Context? {
        return this
    }

    fun getActivity(): BaseActivity<V, VM> {
        return this
    }

    /** 设置状态栏效果  */
    open fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .keyboardEnable(true, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            .navigationBarDarkIcon(true)
            .navigationBarColor(android.R.color.white)
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


    companion object {
        const val CUSTOM_POP_ENTER_TRANSITION_ANIMATION_TAG = "custom_pop_enter_transition_animation"
        const val CUSTOM_EXIT_TRANSITION_ANIMATION_TAG = "custom_exit_transition_animation"
    }

}