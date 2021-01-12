package com.ubitar.app.demo5.vm

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ToastUtils
import com.ubitar.app.demo5.Demo5Activity
import com.ubitar.app.demo5.event.TestEvent
import com.ubitar.app.demo5.fragment.Demo5RootFragment
import com.ubitar.capybara.mvvm.action.FragmentActions
import com.ubitar.capybara.mvvm.model.BaseModel
import com.ubitar.capybara.mvvm.vm.BaseFragmentViewModel

import com.jeremyliao.liveeventbus.LiveEventBus

class Demo5FirstViewModel(application: Application) :
    BaseFragmentViewModel<BaseModel>(application) {
    override fun getModel(): Class<BaseModel>? = null

    override fun onCreateActions(): FragmentActions? = null

    var position = MutableLiveData(1)

    override fun initEvent(lifecycle: LifecycleOwner) {
        super.initEvent(lifecycle)
        LiveEventBus.get(TestEvent::class.java)
            .observe(lifecycle, Observer {
                ToastUtils.showShort("接收到消息了")
            })
    }

    fun onClickTxt1(view: View) {
        start(Demo5RootFragment(), true)
    }

    fun onClickTxt2(view: View) {
        val intent=Intent(getApplication<Application>(),Demo5Activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        getApplication<Application>().startActivity(intent)
    }

    fun onClickTxt3(view: View) {
        LiveEventBus.get(TestEvent::class.java).post(TestEvent())

    }


}