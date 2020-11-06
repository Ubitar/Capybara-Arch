package com.ubitar.capybara.mvvm.dispatcher

import android.view.KeyEvent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ubitar.capybara.mvvm.fragment.BaseFragment

class OnKeyDownDispatcher {

    fun onKeyDown(fragmentManager: FragmentManager, keyCode: Int, event: KeyEvent?) :Boolean{
        return onKeyDown(fragmentManager.fragments, keyCode, event)
    }

    private fun onKeyDown(
        rootFragmentList: List<Fragment>?,
        keyCode: Int,
        event: KeyEvent?
    ): Boolean {
        if (rootFragmentList != null) {
            for (fragment in rootFragmentList) {
                if (fragment == null) continue
                if (fragment is BaseFragment<*, *>) {
                    val isPropagated = onKeyDown(
                        fragment.childFragmentManager.fragments,
                        keyCode,
                        event
                    )
                    if (isPropagated) return isPropagated
                    else return fragment.onKeyDown(keyCode, event)
                }
            }
        }
        return false
    }


}