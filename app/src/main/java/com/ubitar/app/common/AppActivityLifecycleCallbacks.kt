package com.ubitar.app.common

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar
import com.noober.background.BackgroundLibrary

class AppActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    private val fragmentLifecycleCallbacks = AppFragmentLifecycleCallbacks()

    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
        BackgroundLibrary.inject(activity)
        if (activity is AppCompatActivity)
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, true)
        activity::class.java.getAnnotation(IImmersionbar::class.java)?.let {
            ImmersionBar.with(activity).init()
        }
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (activity is AppCompatActivity)
            activity.supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks)
    }
}