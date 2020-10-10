package com.ubitar.capybara.mvvm.control

import android.content.Context
import androidx.lifecycle.LifecycleOwner

/**
 * 控制别人的控制者
 */
interface IController : LifecycleOwner {
    fun showLoading(
        isOutsideEnable: Boolean,
        isBackEnable: Boolean,
        onCanceledListener: (() -> Unit)?,
        extra: Array<out Any?>
    )

    fun hideLoading()

    fun showMessage(text: String, extra: Array<out Any?>)

    fun showSuccess(text: String, extra: Array<out Any?>)

    fun showFail(text: String, extra: Array<out Any?>)

    fun onDestroyController()

    fun getContext(): Context?
}