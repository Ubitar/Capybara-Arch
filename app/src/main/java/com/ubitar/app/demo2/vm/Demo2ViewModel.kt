package com.ubitar.app.demo2.vm

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.ubitar.capybara.mvvm.action.ActivityActions
import com.ubitar.capybara.mvvm.model.BaseModel
import com.ubitar.capybara.mvvm.vm.base.BaseActivityViewModel

class Demo2ViewModel(application: Application) : BaseActivityViewModel<BaseModel>(application) {

    /** 这个是 MVVM 中的  Model层，如没有网络或数据库需求，传NUll即可 */
    override fun getModel(): Class<BaseModel>? = null

    override fun onCreateActions(): ActivityActions? = null

    var showCustomDialogAction = MutableLiveData<Int>()

    fun onClickBtn1(view: View) {
        showLoading(false, true, {
            showMessage("加载框关闭了")
        })
    }

    fun onClickBtn2(view: View) {
        showCustomDialogAction.value = null
    }



}