package com.ubitar.capybara.mvvm.dispatcher

import android.view.KeyEvent
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.ubitar.capybara.mvvm.fragment.BaseFragment
import org.jetbrains.annotations.NotNull

class OnKeyUpDispatcher {

    fun onKeyUp(fragmentManager: FragmentManager, keyCode: Int, event: KeyEvent?) :Boolean{
        return onKeyUp(fragmentManager.fragments, keyCode, event)
    }

    private fun onKeyUp(
        rootFragmentList: List<Fragment>?,
        keyCode: Int,
        event: KeyEvent?
    ): Boolean {
        if (rootFragmentList != null) {
            for (fragment in rootFragmentList) {
                if (fragment == null) continue
                if (fragment is BaseFragment<*, *>) {
                    val isPropagated = onKeyUp(
                        fragment.childFragmentManager.fragments,
                        keyCode,
                        event
                    )
                    if (isPropagated) return isPropagated
                    else return fragment.onKeyUp(keyCode, event)
                }
            }
        }
        return false
    }

}