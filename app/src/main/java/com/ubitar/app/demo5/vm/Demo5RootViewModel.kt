package com.ubitar.app.demo5.vm

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.ubitar.capybara.mvvm.action.FragmentActions
import com.ubitar.capybara.mvvm.model.BaseModel
import com.ubitar.capybara.mvvm.vm.BaseFragmentViewModel

class Demo5RootViewModel(application: Application) : BaseFragmentViewModel<BaseModel>(application) {

    var currentItem = MutableLiveData(0)

    override fun onCreateActions(): FragmentActions? = null

    override fun getModel(): Class<BaseModel>? = null

    fun onClickBack(view: View) {
        onBackPressedSupport()
    }

    fun onClickBtn1(view: View) {
        currentItem.value = 0
    }

    fun onClickBtn2(view: View) {
        currentItem.value = 1
    }

    fun onClickBtn3(view: View) {
        currentItem.value = 2
    }

}