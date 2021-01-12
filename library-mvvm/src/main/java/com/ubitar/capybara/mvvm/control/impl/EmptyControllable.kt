package com.ubitar.capybara.mvvm.control.impl

class EmptyControllable: BaseControllable() {
    override fun onCreate() {
    }

    override fun showLoading(isOutsideEnable: Boolean, isBackEnable: Boolean, onCanceledListener: (() -> Unit)?, extra: Array<out Any?>) {
    }

    override fun hideLoading() {
    }

    override fun showMessage(text: String, onDismissListener: (() -> Unit)?, extra: Array<out Any?>) {
    }

    override fun showSuccess(text: String, onDismissListener: (() -> Unit)?, extra: Array<out Any?>) {
    }

    override fun showFail(text: String, onDismissListener: (() -> Unit)?, extra: Array<out Any?>) {
    }

    override fun onDestroy() {
    }
}