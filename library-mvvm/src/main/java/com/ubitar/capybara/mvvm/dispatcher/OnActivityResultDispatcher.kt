package com.ubitar.capybara.mvvm.dispatcher

import android.annotation.SuppressLint
import android.content.Intent
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import org.jetbrains.annotations.NotNull

class OnActivityResultDispatcher {

    @ExperimentalStdlibApi
    private val mOnActivityResultCallbacks = ArrayDeque<OnActivityResultCallback>()

    fun addCallback(@NotNull owner: LifecycleOwner, onActivityResultCallback: OnActivityResultCallback) {
        val lifecycle = owner.lifecycle
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            return
        }
        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_DESTROY) lifecycle.removeObserver(this)
            }
        })
    }

    fun onActivityResult(fragmentManager: FragmentManager, requestCode: Int, resultCode: Int, data: Intent?) {
        onActivityResultForFragment(fragmentManager.fragments, requestCode, resultCode, data)
    }

    private fun onActivityResultForFragment(
        rootFragmentList: List<Fragment>?,
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (rootFragmentList != null) {
            for (fragment in rootFragmentList) {
                if (fragment == null) continue
                fragment.onActivityResult(requestCode, resultCode, data)
                onActivityResultForFragment(
                    fragment.childFragmentManager.fragments,
                    requestCode,
                    resultCode,
                    data
                )
            }
        }
    }

    interface OnActivityResultCallback {
        @MainThread
        fun handleOnActivityResult()
    }

}