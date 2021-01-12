package com.ubitar.capybara.mvvm.control

/**
 * 通用控制器的执行内容描述
 */
interface IControllable {

    fun onCreate()

    fun showLoading(
        isOutsideEnable: Boolean = false,
        isBackEnable: Boolean = true,
        onCanceledListener: (() -> Unit)? = null,
        extra: Array<out Any?>
    )

    fun hideLoading()

    fun showMessage(text: String, onDismissListener: (() -> Unit)? = null, extra: Array<out Any?>)

    fun showSuccess(text: String, onDismissListener: (() -> Unit)? = null, extra: Array<out Any?>)

    fun showFail(text: String,  onDismissListener: (() -> Unit)? = null,extra: Array<out Any?>)

    fun onDestroy()

}