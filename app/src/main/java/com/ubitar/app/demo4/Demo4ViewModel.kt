package com.ubitar.app.demo4

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.ubitar.capybara.mvvm.action.ActivityActions
import com.ubitar.capybara.mvvm.vm.BaseActivityViewModel

class Demo4ViewModel(application: Application) : BaseActivityViewModel<Demo4Model>(application) {

    override fun getModel(): Class<Demo4Model>? = Demo4Model::class.java

    override fun onCreateActions(): ActivityActions? = null

    var account = MutableLiveData<String>("")
    var password = MutableLiveData<String>("")

    fun onClickBack(view: View) {
        onBackPressedSupport()
    }

    fun onClickLogin(view: View) {
        model.toLogin(account.value ?: "", password.value ?: "")
    }

    fun onClickLogout(view: View) {
        model.toLogout(account.value?:"")
    }

    fun afterLoginSuccess() {
        ToastUtils.showShort("登陆成功")
    }

    fun afterLogoutSuccess() {
        ToastUtils.showShort("注销成功")
    }


}