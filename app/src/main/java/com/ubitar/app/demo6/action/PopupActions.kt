package com.ubitar.app.demo6.action

import com.ubitar.capybara.mvvm.action.SingleLiveAction

open class PopupActions {
    val postAction: PostAction by lazy { PostAction() }
    val dismissAction: DismissAction by lazy { DismissAction() }
    val showAction: ShowAction by lazy { ShowAction() }


    class PostAction : SingleLiveAction<Runnable>() {
        override fun describe(): String {
            return "Context.post()"
        }
    }

    class DismissAction : SingleLiveAction<Any>() {
        override fun describe(): String {
            return "XPopup.dismiss()"
        }
    }

    class ShowAction : SingleLiveAction<Any>() {
        override fun describe(): String {
            return "XPopup.show()"
        }
    }
}