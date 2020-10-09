package com.ubitar.capybara.mvvm.action

open class DialogActions {
    val postAction: PostAction by lazy { PostAction() }
    val dismissAction: DismissAction by lazy { DismissAction() }
    val dismissAllowingStateLossAction: DismissAllowingStateLossAction by lazy { DismissAllowingStateLossAction() }
    val showLoadingAction: ShowLoadingAction by lazy { ShowLoadingAction() }
    val hideLoadingAction: HideLoadingAction by lazy { HideLoadingAction() }
    val showMessageAction: ShowMessageAction by lazy { ShowMessageAction() }


    class PostAction : SingleLiveAction<Runnable>() {
        override fun describe(): String {
            return "Context.post()"
        }
    }


    class DismissAction : SingleLiveAction<Any>() {
        override fun describe(): String {
            return "DialogFragment.dismiss()"
        }
    }

    class DismissAllowingStateLossAction : SingleLiveAction<Any>() {
        override fun describe(): String {
            return "DialogFragment.dismissAllowingStateLoss()"
        }
    }
    class ShowLoadingAction : SingleLiveAction<ShowLoadingAction.ShowLoading>() {

        override fun describe(): String {
            return "IController.showLoading()"
        }

        data class ShowLoading(
            val isCancelEnable: Boolean,
            val isBackEnable: Boolean,
            val listener: (() -> Unit)?
        )

    }
    class HideLoadingAction : SingleLiveAction<Any>() {

        override fun describe(): String {
            return "IController.hideLoading()"
        }

    }
    class ShowMessageAction : SingleLiveAction<String>() {
        override fun describe(): String {
            return "IController.showMessage()"
        }
    }
}