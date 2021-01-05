package com.ubitar.capybara.mvvm.action

import android.content.Intent
import com.weikaiyun.fragmentation.ISupportFragment

open class ActivityActions {
    val finishAction: FinishAction by lazy { FinishAction() }
    val finishAfterTransitionAction: FinishAfterTransitionAction by lazy { FinishAfterTransitionAction() }
    val onBackPressedSupportAction: OnBackPressedSupportAction by lazy { OnBackPressedSupportAction() }
    val setResultAction: SetResultAction by lazy { SetResultAction() }
    val postAction: PostAction by lazy { PostAction() }
    val loadRootFragmentAction: LoadRootFragmentAction by lazy { LoadRootFragmentAction() }
    val startAction: StartAction by lazy { StartAction() }
    val startWithPopToAction: StartWithPopToAction by lazy { StartWithPopToAction() }
    val popAction: PopAction by lazy { PopAction() }
    val popToAction: PopToAction by lazy { PopToAction() }
    val showLoadingAction: ShowLoadingAction by lazy { ShowLoadingAction() }
    val hideLoadingAction: HideLoadingAction by lazy { HideLoadingAction() }
    val showMessageAction: ShowMessageAction by lazy { ShowMessageAction() }
    val showSuccessAction: ShowSuccessAction by lazy { ShowSuccessAction() }
    val showFailAction: ShowFailAction by lazy { ShowFailAction() }

    class OnBackPressedSupportAction : SingleLiveAction<Any>() {
        override fun describe(): String {
            return "ISupportFragment.onBackPressedSupport()"
        }
    }

    class PopAction : SingleLiveAction<Any>() {
        override fun describe(): String {
            return "ISupportFragment.pop()方法"
        }

    }

    class PostAction : SingleLiveAction<Runnable>() {
        override fun describe(): String {
            return "Context.post()"
        }
    }

    class StartAction : SingleLiveAction<StartAction.Start>() {

        override fun describe(): String {
            return "ISupportFragment.start()"
        }

        data class Start(
            val toFragment: ISupportFragment,
            val fromParentFragment: Boolean = false,
            val launchMode: Int? = null
        )
    }

    class FinishAction : SingleLiveAction<Any>() {
        override fun describe(): String {
            return "Activity.finish()方法"
        }

    }

    class FinishAfterTransitionAction : SingleLiveAction<Any>() {
        override fun describe(): String {
            return "ActivityCompact.finishAfterTransition()"
        }
    }

    class LoadRootFragmentAction : SingleLiveAction<LoadRootFragmentAction.LoadRootFragment>() {
        override fun describe(): String {
            return "Activity.loadRootFragment()"
        }

        data class LoadRootFragment(
            val containerId: Int,
            val toFragment: ISupportFragment
        )
    }

    class PopToAction : SingleLiveAction<PopToAction.PopTo>() {
        override fun describe(): String {
            return "Activity.popTo()方法"
        }

        data class PopTo(
            val targetFragmentClass: Class<*>,
            val includeTargetFragment: Boolean,
            val afterPopTransactionRunnable: Runnable? = null
        )

    }

    class SetResultAction : SingleLiveAction<SetResultAction.SetResult>() {

        override fun describe(): String {
            return "Activity.setResult()"
        }

        data class SetResult(
            val result: Int,
            val data: Intent? = null
        )

    }

    class StartWithPopToAction : SingleLiveAction<StartWithPopToAction.StartWithPopTo>() {
        override fun describe(): String {
            return "Activity.startWithPopTo()"
        }

        data class StartWithPopTo(
            val toFragment: ISupportFragment,
            val targetFragmentClass: Class<*>,
            val includeTargetFragment: Boolean
        )

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
