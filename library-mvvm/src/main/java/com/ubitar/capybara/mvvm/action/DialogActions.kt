package com.ubitar.capybara.mvvm.action

open class DialogActions {
    val postAction: PostAction by lazy { PostAction() }
    val dismissAction: DismissAction by lazy { DismissAction() }
    val dismissAllowingStateLossAction: DismissAllowingStateLossAction by lazy { DismissAllowingStateLossAction() }
    val showLoadingAction: ShowLoadingAction by lazy { ShowLoadingAction() }
    val hideLoadingAction: HideLoadingAction by lazy { HideLoadingAction() }
    val showMessageAction: ShowMessageAction by lazy { ShowMessageAction() }
    val showSuccessAction: ShowSuccessAction by lazy { ShowSuccessAction() }
    val showFailAction: ShowFailAction by lazy { ShowFailAction() }


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
            val dismissListener: (() -> Unit)?,
            val extra: Array<out Any?>
        )
    }

    class HideLoadingAction : SingleLiveAction<Any>() {

        override fun describe(): String {
            return "IController.hideLoading()"
        }

    }

    class ShowMessageAction : SingleLiveAction<ShowMessageAction.ShowMessage>() {
        override fun describe(): String {
            return "IController.showMessage()"
        }
        data class ShowMessage(
            val text: String,
            val onDismissListener:(()->Unit)?=null,
            val extra: Array<out Any?>
        )
    }

    class ShowSuccessAction : SingleLiveAction<ShowSuccessAction.ShowSuccess>() {
        override fun describe(): String {
            return "IController.showSuccess()"
        }

        data class ShowSuccess(
            val text: String,
            val onDismissListener:(()->Unit)?=null,
            val extra: Array<out Any?>
        )
    }

    class ShowFailAction : SingleLiveAction<ShowFailAction.ShowFail>() {
        override fun describe(): String {
            return "IController.showFail()"
        }

        data class ShowFail(
            val text: String,
            val onDismissListener:(()->Unit)?=null,
            val extra: Array<out Any?>
        )
    }
}