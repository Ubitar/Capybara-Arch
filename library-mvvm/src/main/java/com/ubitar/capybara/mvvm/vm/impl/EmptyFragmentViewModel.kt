package com.ubitar.capybara.mvvm.vm.impl

import android.app.Application
import com.ubitar.capybara.mvvm.action.FragmentActions
import com.ubitar.capybara.mvvm.model.BaseModel
import com.ubitar.capybara.mvvm.vm.BaseFragmentViewModel

class EmptyFragmentViewModel(application: Application) :
    BaseFragmentViewModel<BaseModel>(
        application
    ) {
    override fun getModel(): Class<BaseModel>? = null

    override fun onCreateActions(): FragmentActions? =null

}