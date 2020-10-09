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
        extra: Any? = null
    )

    fun hideLoading()

    fun showMessage(text: String, extra: Any? = null)

    fun onDestroy()

}