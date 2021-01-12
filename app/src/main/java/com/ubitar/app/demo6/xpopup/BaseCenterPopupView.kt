package com.ubitar.app.demo6.xpopup

import androidx.databinding.ViewDataBinding
import com.ubitar.app.demo6.vm.BasePopupViewModel
import com.ubitar.capybara.mvvm.control.impl.ControllableProvider
import com.ubitar.capybara.mvvm.control.IController

abstract class BaseCenterPopupView<V : ViewDataBinding, VM : BasePopupViewModel<*>>(parent: IController) :
    BaseCenterMvvMPopupView<V, VM>
        (parent) {

    protected lateinit var controllerProvider: ControllableProvider

    override fun onCreate() {
        super.onCreate()
        controllerProvider = ControllableProvider.with(parent.get()!!)
        controllerProvider.get().onCreate()
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

    override fun onDestroy() {
        controllerProvider.get().onDestroy()
        super.onDestroy()
    }

    override fun showLoading(isOutsideEnable: Boolean, isBackEnable: Boolean, onCanceledListener: (() -> Unit)?, extra: Array<out Any?>) {
        controllerProvider.get().showLoading( isOutsideEnable, isBackEnable, onCanceledListener, extra)
    }

    override fun showSuccess(text: String, onDismissListener: (() -> Unit)?, extra: Array<out Any?>) {
        controllerProvider.get().showSuccess(text, onDismissListener, extra)
    }

    override fun showFail(text: String, onDismissListener: (() -> Unit)?, extra: Array<out Any?>) {
        controllerProvider.get().showFail(text, onDismissListener, extra)
    }

    override fun hideLoading() {
        controllerProvider.get().hideLoading()
    }

    override fun showMessage(text: String, onDismissListener: (() -> Unit)?, extra: Array<out Any?>) {
        controllerProvider.get().showMessage(text, onDismissListener, extra)
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