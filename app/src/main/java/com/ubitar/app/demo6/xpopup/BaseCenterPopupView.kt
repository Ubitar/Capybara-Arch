package com.ubitar.app.demo6.xpopup

import androidx.databinding.ViewDataBinding
import com.ubitar.app.demo6.vm.BasePopupViewModel
import com.ubitar.capybara.mvvm.control.impl.ControllableProvider
import com.ubitar.capybara.mvvm.control.IController

abstract class BaseCenterPopupView<V : ViewDataBinding, VM : BasePopupViewModel<*>>(parent: IController) :
    BaseCenterMvvMPopupView<V, VM>
        (parent) {

    protected var controllableProvider: ControllableProvider?=null

    override fun onCreate() {
        super.onCreate()
        controllableProvider = ControllableProvider.with(parent.get()!!)
        controllableProvider?.get()?.onCreate()
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

    override fun onShow() {
        super.onShow()
    }

    override fun onDismiss() {
        super.onDismiss()
    }

    override fun onDetachedFromWindow() {
        controllableProvider?.get()?.onDestroy()
        controllableProvider=null
        super.onDetachedFromWindow()
    }

    override fun showLoading(isOutsideEnable: Boolean, isBackEnable: Boolean, onCanceledListener: (() -> Unit)?, extra: Array<out Any?>) {
        controllableProvider?.get()?.showLoading( isOutsideEnable, isBackEnable, onCanceledListener, extra)
    }

    override fun showSuccess(text: String, onDismissListener: (() -> Unit)?, extra: Array<out Any?>) {
        controllableProvider?.get()?.showSuccess(text, onDismissListener, extra)
    }

    override fun showFail(text: String, onDismissListener: (() -> Unit)?, extra: Array<out Any?>) {
        controllableProvider?.get()?.showFail(text, onDismissListener, extra)
    }

    override fun hideLoading() {
        controllableProvider?.get()?.hideLoading()
    }

    override fun showMessage(text: String, onDismissListener: (() -> Unit)?, extra: Array<out Any?>) {
        controllableProvider?.get()?.showMessage(text, onDismissListener, extra)
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