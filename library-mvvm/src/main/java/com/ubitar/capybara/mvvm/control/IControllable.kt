package com.ubitar.capybara.mvvm.control

/**
 * 被别人控制
 */
interface IControllable {

    fun showLoading(
        controller: IController,
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