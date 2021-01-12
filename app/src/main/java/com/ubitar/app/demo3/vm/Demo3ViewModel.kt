package com.ubitar.app.demo3.vm

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.ubitar.app.demo3.adapter.Demo3Adapter
import com.ubitar.app.demo3.bean.ListBean
import com.ubitar.capybara.mvvm.action.ActivityActions
import com.ubitar.capybara.mvvm.model.BaseModel
import com.ubitar.capybara.mvvm.vm.BaseActivityViewModel

class Demo3ViewModel(application: Application) : BaseActivityViewModel<BaseModel>(application) {

    /** 这个是 MVVM 中的  Model层，如没有网络或数据库需求，传NUll即可 */
    override fun getModel(): Class<BaseModel>? = null

    override fun onCreateActions(): ActivityActions? = null

    var adapter = MutableLiveData<Demo3Adapter>(Demo3Adapter())

    override fun initEvent(lifecycle: LifecycleOwner) {
        super.initEvent(lifecycle)
        adapter.value?.setOnItemClickListener { adapter, view, position ->
            ToastUtils.showShort("点击了")
        }
    }

    override fun initData() {
        super.initData()
        val list = arrayListOf(
            ListBean("这是第一条"),
            ListBean("这是第二条"),
            ListBean("这是第三条")
        )
        adapter.value?.setList(list)
    }

    fun onClickBack(view: View) {
        onBackPressedSupport()
    }

}