package com.ubitar.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.blankj.utilcode.util.ActivityUtils

import com.blankj.utilcode.util.ProcessUtils
import com.blankj.utilcode.util.Utils
import com.noober.background.BackgroundLibrary
import com.ubitar.capybara.mvvm.control.ControlConfig
import com.ubitar.capybara.mvvm.control.ControlProvider
import com.ubitar.capybara.network.NetworkManager
import me.yokeyword.fragmentation.Fragmentation

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        /** 这个是防止多进程时，多次初始化App*/
        if (packageName != ProcessUtils.getCurrentProcessName()) return

        instance = this

        /** 初始化工具类 */
        Utils.init(this)

        NetworkManager.init(Host.DEFAULT_HOST, {
            //此处可以添加Logger拦截器
        }, {
            //自定义统一处理网络请求码错误
            //返回null表示不使用自定义处理
            null
        }, {
            //自定义处理网络接口返回值
            //返回null表示不使用自定义处理
            null
        })

        Fragmentation.builder()
            .stackViewMode(Fragmentation.SHAKE)
            .debug(BuildConfig.DEBUG)
            .install()

        ControlProvider.setGlobalConfig(ControlConfig().setGlobalControl(AppControllable()))

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                BackgroundLibrary.inject(p0)
            }

            override fun onActivityStarted(p0: Activity) {
            }

            override fun onActivityResumed(p0: Activity) {
            }

            override fun onActivityPaused(p0: Activity) {
            }

            override fun onActivityStopped(p0: Activity) {
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
            }

            override fun onActivityDestroyed(p0: Activity) {
            }

        })
    }


    companion object {
        var instance: App? = null
            private set
    }
}