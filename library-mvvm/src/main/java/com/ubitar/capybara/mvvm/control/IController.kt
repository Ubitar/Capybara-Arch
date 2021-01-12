package com.ubitar.capybara.mvvm.control

import android.content.Context
import androidx.lifecycle.LifecycleOwner

/**
 * 通用控制器的实际执行者
 */
interface IController : LifecycleOwner {
    fun showLoading(
        isOutsideEnable: Boolean,
        isBackEnable: Boolean,
        onCanceledListener: (() -> Unit)?,
        extra: Array<out Any?>
    )

    fun hideLoading()

    fun showMessage(text: String, onDismissListener: (() -> Unit)? = null, extra: Array<out Any?>)

    fun showSuccess(text: String, onDismissListener: (() -> Unit)? = null, extra: Array<out Any?>)

    fun showFail(text: String, onDismissListener: (() -> Unit)? = null, extra: Array<out Any?>)

    fun getContext(): Context?
}