package com.ubitar.app.common

import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.gyf.immersionbar.ImmersionBar

class AppFragmentLifecycleCallbacks : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        super.onFragmentResumed(fm, f)
        f::class.java.getAnnotation(IImmersionbar::class.java)?.let {
            ImmersionBar.with(f)
                .navigationBarColorInt(Color.WHITE)
                .navigationBarDarkIcon(true)
                .init()
        }
    }

}