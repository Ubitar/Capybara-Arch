package com.ubitar.app.demo2.vm

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.ubitar.capybara.mvvm.action.DialogActions
import com.ubitar.capybara.mvvm.model.BaseModel
import com.ubitar.capybara.mvvm.vm.BaseDialogViewModel

class Dem2DialogViewModel(application: Application) : BaseDialogViewModel<BaseModel>(application) {

    /** 这个是 MVVM 中的  Model层，如没有网络或数据库需求，传NUll即可 */
    override fun getModel(): Class<BaseModel>? = null

    override fun onCreateActions(): DialogActions? = null

    var edtContent = MutableLiveData("")

    fun onClickBtn(view: View) {
        dismissAllowingStateLoss()
//        dismiss()
    }

}