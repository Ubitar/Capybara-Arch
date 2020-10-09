package com.ubitar.app.demo6.myxpopup

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ubitar.app.demo6.action.PopupActions
import com.ubitar.app.demo6.vm.BasePopupViewModel
import com.ubitar.capybara.mvvm.model.EmptyModel

class LoadingPopupViewModel(application: Application) :
    BasePopupViewModel<EmptyModel>(application) {

    override fun getModel(): Class<EmptyModel>? = null

    override fun onCreateActions(): PopupActions? = null

    val params = MutableLiveData<LoadingPopup.Params>()


}