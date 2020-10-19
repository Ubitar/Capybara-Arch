package com.ubitar.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.blankj.utilcode.util.KeyboardUtils

class AppFragmentLifecycleCallbacks: FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        KeyboardUtils.hideSoftInput(f.requireActivity())
    }

}