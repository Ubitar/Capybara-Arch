package com.ubitar.app

import android.app.Application
import com.blankj.utilcode.util.ProcessUtils
import com.blankj.utilcode.util.Utils
import com.ubitar.app.common.AppActivityLifecycleCallbacks
import com.ubitar.app.common.AppControllable
import com.ubitar.app.common.Host
import com.ubitar.app.common.NetworkTag
import com.ubitar.capybara.mvvm.CMVVM
import com.ubitar.capybara.network.Server
import com.ubitar.capybara.network.NetworkManager
import com.weikaiyun.fragmentation.Fragmentation

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        /** 这个是防止多进程时，多次初始化App*/
        if (packageName != ProcessUtils.getCurrentProcessName()) return

        instance = this

        /** 初始化工具类 */
        Utils.init(this)

        NetworkManager.getInstance()
            .addServer(NetworkTag.TAG1, Server.create(Host.DEFAULT_HOST_URL, {
                //此处可以添加Logger拦截器或修改响应时间
//                it.addInterceptor(LoggerInterceptor())
//                it.addInterceptor(TokenInterceptor())
            }))

        //初始化Fragmentation
        val fragmentation=Fragmentation.builder()
            .stackViewMode(Fragmentation.SHAKE)
            .debug(BuildConfig.DEBUG)
            .animation(R.anim.h_fragment_enter, R.anim.h_fragment_pop_exit, R.anim.h_fragment_pop_enter, R.anim.h_fragment_exit)
            .install()

        CMVVM.builder()
            .setGlobalControllable(AppControllable::class.java)
            .setFragmentation(fragmentation)
            .install()

        registerActivityLifecycleCallbacks(AppActivityLifecycleCallbacks())
    }


    companion object {
        var instance: App? = null
            private set
    }
}