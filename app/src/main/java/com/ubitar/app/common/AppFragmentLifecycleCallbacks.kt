package com.ubitar.app.common

import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.blankj.utilcode.util.KeyboardUtils
import com.gyf.immersionbar.ImmersionBar

class AppFragmentLifecycleCallbacks : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        super.onFragmentResumed(fm, f)
        if (f is IImmersionbar) {
            val immersionBar = ImmersionBar.with(f)
            immersionBar.navigationBarColorInt(Color.WHITE)
            immersionBar.navigationBarDarkIcon(true)
            immersionBar.init()
        }
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        KeyboardUtils.hideSoftInput(f.requireActivity())
    }

}