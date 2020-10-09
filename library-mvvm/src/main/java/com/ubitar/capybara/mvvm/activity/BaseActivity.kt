package com.ubitar.capybara.mvvm.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

import com.blankj.utilcode.util.KeyboardUtils
import com.gyf.immersionbar.ImmersionBar
import com.ubitar.capybara.mvvm.common.ActivityManager
import com.ubitar.capybara.mvvm.control.ControlProvider
import com.ubitar.capybara.mvvm.vm.base.BaseActivityViewModel
import com.noober.background.BackgroundLibrary
import com.ubitar.capybara.mvvm.R

abstract class BaseActivity<V : ViewDataBinding, VM : BaseActivityViewModel<*>> :
    BaseMvvMActivity<V, VM>() {

    protected lateinit var controllerProvider: ControlProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        BackgroundLibrary.inject(this)
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
        KeyboardUtils.hideSoftInput(this)
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

    override fun showLoading(
        isCancelEnable: Boolean,
        isBackEnable: Boolean,
        onCanceledListener: (() -> Unit)?,
        extra: Any?
    ) {
        controllerProvider.get().showLoading(this, isCancelEnable, isBackEnable, onCanceledListener, extra)
    }

    override fun hideLoading() {
        controllerProvider.get().hideLoading()
    }

    override fun showMessage(text: String, extra: Any?) {
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