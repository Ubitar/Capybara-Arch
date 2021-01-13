package com.ubitar.capybara.mvvm.vm

import android.app.Application
import android.content.Intent
import com.ubitar.capybara.mvvm.action.ActivityActions
import com.ubitar.capybara.mvvm.model.BaseModel
import com.weikaiyun.fragmentation.ISupportFragment
import java.lang.ref.WeakReference

abstract class BaseActivityViewModel<M : BaseModel>(application: Application) :
    BaseViewModel<M>(application) {

    private lateinit var baseActions: ActivityActions

    fun finish() {
        baseActions.finishAction.call()
    }

    fun finishAfterTransition() {
        baseActions.finishAfterTransitionAction.call()
    }

    fun onBackPressedSupport() {
        baseActions.onBackPressedSupportAction.call()
    }

    fun setResult(resultCode: Int) {
        baseActions.setResultAction.call(ActivityActions.SetResultAction.SetResult(resultCode))
    }

    fun setResult(resultCode: Int, data: Intent) {
        baseActions.setResultAction.call(
            ActivityActions.SetResultAction.SetResult(
                resultCode,
                data
            )
        )
    }

    fun post(runnable: Runnable) {
        baseActions.postAction.call(runnable)
    }

    fun loadRootFragment(containerId: Int, toFragment: ISupportFragment) {
        baseActions.loadRootFragmentAction.call(
            ActivityActions.LoadRootFragmentAction.LoadRootFragment(
                containerId,
                    WeakReference(toFragment)
            )
        )
    }

    fun start(toFragment: ISupportFragment) {
        baseActions.startAction.call(ActivityActions.StartAction.Start(WeakReference(toFragment)))
    }

    fun start(toFragment: ISupportFragment, @ISupportFragment.LaunchMode launchMode: Int) {
        baseActions.startAction.call(
            ActivityActions.StartAction.Start(
                    WeakReference(toFragment),
                false,
                launchMode
            )
        )
    }

    fun startWithPopTo(
        toFragment: ISupportFragment,
        targetFragmentClass: Class<*>,
        includeTargetFragment: Boolean
    ) {
        baseActions.startWithPopToAction.call(
            ActivityActions.StartWithPopToAction.StartWithPopTo(
                WeakReference(toFragment),
                targetFragmentClass,
                includeTargetFragment
            )
        )
    }

    fun pop() {
        baseActions.popAction.call()
    }

    fun popTo(targetFragmentClass: Class<*>, includeTargetFragment: Boolean) {
        baseActions.popToAction.call(
            ActivityActions.PopToAction.PopTo(
                targetFragmentClass,
                includeTargetFragment
            )
        )
    }

    fun popTo(
        targetFragmentClass: Class<*>,
        includeTargetFragment: Boolean,
        afterPopTransactionRunnable: Runnable
    ) {
        baseActions.popToAction.call(
            ActivityActions.PopToAction.PopTo(
                targetFragmentClass,
                includeTargetFragment,
                    WeakReference(afterPopTransactionRunnable)
            )
        )
    }

    fun showLoading(
        isCancelEnable: Boolean = false,
        isBackEnable: Boolean = true,
        dismissListener: (() -> Unit)? = null,
        vararg extra: Any?
    ) {
        baseActions.showLoadingAction.call(
            ActivityActions.ShowLoadingAction.ShowLoading(
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
        onDismissListener:(()->Unit)?=null,
        vararg extra: Any?
    ) {
        baseActions.showMessageAction.call(ActivityActions.ShowMessageAction.ShowMessage(text,onDismissListener,extra))
    }

    fun showSuccess(
        text: String,
        onDismissListener:(()->Unit)?=null,
        vararg extra: Any?
    ) {
        baseActions.showSuccessAction.call(
            ActivityActions.ShowSuccessAction.ShowSuccess(text,onDismissListener, extra)
        )
    }

    fun showFail(
        text: String,
        onDismissListener:(()->Unit)?=null,
        vararg extra: Any?
    ) {
        baseActions.showFailAction.call(
            ActivityActions.ShowFailAction.ShowFail(text,onDismissListener, extra)
        )
    }

    fun getBaseActions(): ActivityActions {
        return baseActions
    }

    internal fun injectBaseActions(actions: ActivityActions?) {
        baseActions = actions ?: ActivityActions()
    }

    /** 创建自己的Action，并继承ActionActions */
    abstract fun onCreateActions(): ActivityActions?


}