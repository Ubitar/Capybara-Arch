package com.ubitar.capybara.mvvm.vm.impl

import android.app.Application
import com.ubitar.capybara.mvvm.action.ActivityActions
import com.ubitar.capybara.mvvm.model.BaseModel
import com.ubitar.capybara.mvvm.vm.BaseActivityViewModel

class EmptyActivityViewModel(application: Application) :
    BaseActivityViewModel<BaseModel>(
        application
    ) {

    override fun getModel(): Class<BaseModel>? = null

    override fun onCreateActions(): ActivityActions? =null

}