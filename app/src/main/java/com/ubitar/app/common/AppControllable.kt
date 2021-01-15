package com.ubitar.app.common

import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import com.ubitar.app.demo6.myxpopup.LoadingPopup
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.interfaces.SimpleCallback
import com.ubitar.capybara.mvvm.control.impl.BaseControllable
import java.lang.ref.WeakReference

class AppControllable : BaseControllable() {
    private var loadingPopup: WeakReference<BasePopupView>? = null

    override fun onCreate() {
    }

    override fun showLoading(
        isOutsideEnable: Boolean,
        isBackEnable: Boolean,
        onCanceledListener: (() -> Unit)?,
        extra: Array<out Any?>
    ) {
        hideLoading()
        val activity = ActivityUtils.getTopActivity()
        if (ActivityUtils.isActivityAlive(activity)) {
            loadingPopup = WeakReference(
                XPopup.Builder(controller.getContext())
                    .hasShadowBg(false)
                    .dismissOnTouchOutside(isOutsideEnable)
                    .dismissOnBackPressed(isBackEnable)
                    .setPopupCallback(object : SimpleCallback() {
                        override fun onBackPressed(): Boolean {
                            onCanceledListener?.invoke()
                            return false
                        }
                    })
                    .asCustom(LoadingPopup.Builder().setText("加载中").build(controller))
                    .show()
            )
        }
    }

    override fun hideLoading() {
        val activity = ActivityUtils.getTopActivity()
        if (ActivityUtils.isActivityAlive(activity))
            loadingPopup?.get()?.dismiss()
    }

    override fun showMessage(
        text: String,
        onDismissListener: (() -> Unit)?,
        extra: Array<out Any?>
    ) {
        ToastUtils.showShort(text)
    }

    override fun showSuccess(
        text: String,
        onDismissListener: (() -> Unit)?,
        extra: Array<out Any?>
    ) {
    }

    override fun showFail(text: String, onDismissListener: (() -> Unit)?, extra: Array<out Any?>) {
    }

    override fun onDestroy() {
        hideLoading()
        loadingPopup?.clear()
    }
}