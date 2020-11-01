package com.ubitar.app

import android.app.Application
import com.blankj.utilcode.util.ProcessUtils
import com.blankj.utilcode.util.Utils
import com.ubitar.app.common.AppActivityLifecycleCallbacks
import com.ubitar.app.common.AppControllable
import com.ubitar.app.common.Host
import com.ubitar.app.common.NetworkTag
import com.ubitar.capybara.mvvm.control.ControlConfig
import com.ubitar.capybara.mvvm.control.ControlProvider
import com.ubitar.capybara.network.Server
import com.ubitar.capybara.network.NetworkManager

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
            }, {
                //自定义统一处理网络请求码错误
                //返回null表示不添加自定义处理规则
                //return ApiException()
                null
            }, {
                //自定义处理网络接口返回值
                //返回null表示不添加自定义处理规则
                //  it.setValue(xx)
                //return Flowable.just(it)
                null
            }))


        //初始化Fragmentation
//        Fragmentation.builder()
//            .stackViewMode(Fragmentation.SHAKE)
//            .debug(BuildConfig.DEBUG)
//            .install()

        ControlProvider.setGlobalConfig(ControlConfig().setGlobalControl(AppControllable()))

        registerActivityLifecycleCallbacks(AppActivityLifecycleCallbacks())
    }


    companion object {
        var instance: App? = null
            private set
    }
}