package com.ubitar.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.FragmentUtils
import com.blankj.utilcode.util.KeyboardUtils
import com.noober.background.BackgroundLibrary
import com.ubitar.capybara.mvvm.activity.BaseActivity

class AppActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    private val fragmentLifecycleCallbacks = AppFragmentLifecycleCallbacks()

    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
        BackgroundLibrary.inject(activity)
        if (activity is AppCompatActivity)
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, false)
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
        KeyboardUtils.hideSoftInput(activity)
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