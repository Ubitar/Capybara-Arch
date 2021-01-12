package com.ubitar.capybara.mvvm.vm

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
        dismissListener: (() -> Unit)? = null,
        vararg extra: Any?
    ) {
        baseActions.showLoadingAction.call(
            DialogActions.ShowLoadingAction.ShowLoading(
                isCancelEnable,
                isBackEnable,
                dismissListener,
                extra
            )
        )
    }

    fun hideLoading() {
        baseActions.hideLoadingAction.call()
    }


    fun showMessage(
        text: String,
        onDismissListener: (() -> Unit)? = null,
        vararg extra: Any?
    ) {
        baseActions.showMessageAction.call(DialogActions.ShowMessageAction.ShowMessage(text, onDismissListener, extra))
    }

    fun showSuccess(
        text: String,
        onDismissListener: (() -> Unit)? = null,
        vararg extra: Any?
    ) {
        baseActions.showSuccessAction.call(
            DialogActions.ShowSuccessAction.ShowSuccess(text, onDismissListener, extra)
        )
    }

    fun showFail(
        text: String,
        onDismissListener: (() -> Unit)? = null,
        vararg extra: Any?
    ) {
        baseActions.showFailAction.call(
            DialogActions.ShowFailAction.ShowFail(text, onDismissListener, extra)
        )
    }

    fun getBaseActions(): DialogActions {
        return baseActions
    }

    fun injectBaseActions(actions: DialogActions?) {
        baseActions = actions ?: DialogActions()
    }

    /** 创建自己的Action，并继承DialogActions */
    abstract fun onCreateActions(): DialogActions?

}