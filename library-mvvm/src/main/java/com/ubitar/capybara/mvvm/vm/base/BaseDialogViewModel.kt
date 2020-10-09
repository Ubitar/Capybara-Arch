package com.ubitar.capybara.mvvm.vm.base

import android.app.Application
import com.ubitar.capybara.mvvm.action.DialogActions
import com.ubitar.capybara.mvvm.model.BaseModel

abstract class BaseDialogViewModel<M : BaseModel>(application: Application) :
    BaseViewModel<M>(application) {

    private lateinit var baseActions: DialogActions

    fun post(runnable: Runnable) {
        baseActions.postAction.call(runnable)
    }

    fun dismiss() {
        baseActions.dismissAction.call()
    }

    fun dismissAllowingStateLoss() {
        baseActions.dismissAllowingStateLossAction.call()
    }

    fun showLoading(
        isCancelEnable: Boolean = false,
        isBackEnable: Boolean = true,
        listener: (() -> Unit)? = null
    ) {
        baseActions.showLoadingAction.call(
            DialogActions.ShowLoadingAction.ShowLoading(
                isCancelEnable,
                isBackEnable,
                listener
            )
        )
    }

    fun hideLoading() {
        baseActions.hideLoadingAction.call()
    }

    fun showMessage(text: String) {
        baseActions.showMessageAction.call(text)
    }

    fun getBaseActions(): DialogActions {
        return baseActions
    }

    fun injectBaseActions(actions: DialogActions?) {
        baseActions = actions?:DialogActions()
    }

    /** 创建自己的Action，并继承DialogActions */
    abstract fun onCreateActions(): DialogActions ?

}