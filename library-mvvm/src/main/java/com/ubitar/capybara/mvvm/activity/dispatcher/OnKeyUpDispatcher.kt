package com.ubitar.capybara.mvvm.activity.dispatcher

import android.view.KeyEvent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ubitar.capybara.mvvm.fragment.BaseFragment

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