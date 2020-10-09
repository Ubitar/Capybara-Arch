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
        extra: Any? = null
    )

    fun hideLoading()

    fun showMessage(text: String, extra: Any? = null)

    fun onDestroyController()

    fun getContext(): Context?
}