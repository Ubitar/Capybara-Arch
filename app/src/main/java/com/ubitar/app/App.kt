package com.ubitar.app

import android.app.Application

import com.blankj.utilcode.util.ProcessUtils
import com.blankj.utilcode.util.Utils
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
            null
        }, {
            null
        }, {
            null
        })

        Fragmentation.builder()
            .stackViewMode(Fragmentation.SHAKE)
            .debug(BuildConfig.DEBUG)
            .install()

        ControlProvider.setGlobalConfig(ControlConfig().setGlobalControl(AppControllable()))
    }


    companion object {
        var instance: App? = null
            private set
    }
}