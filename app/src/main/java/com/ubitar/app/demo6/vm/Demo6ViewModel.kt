package com.ubitar.app.demo6.vm

import android.app.Application
import android.view.View
import com.ubitar.capybara.mvvm.action.ActivityActions
import com.ubitar.capybara.mvvm.model.EmptyModel
import com.ubitar.capybara.mvvm.vm.BaseActivityViewModel

class Demo6ViewModel(application: Application) : BaseActivityViewModel<EmptyModel>(application) {
    override fun getModel(): Class<EmptyModel>? = null


    override fun onCreateActions(): ActivityActions? = null

    fun onClickBtn1(view: View) {
        showLoading(false, true,{
            showMessage("加载框关闭了")
        })
    }
}