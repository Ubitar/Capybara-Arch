package com.ubitar.app.demo1

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.ubitar.app.demo1.action.Demo1Actions
import com.ubitar.capybara.mvvm.action.ActivityActions
import com.ubitar.capybara.mvvm.model.BaseModel
import com.ubitar.capybara.mvvm.vm.BaseActivityViewModel

class Demo1ViewModel(application: Application) : BaseActivityViewModel<BaseModel>(application) {

    var valueFromActivity = "这个值从Activity那进行设置注入"

    var edtContent1 = MutableLiveData("")
    var checkBoxValue = MutableLiveData(false)
    var checkBoxText = MutableLiveData("选择后显示下方按钮")
    var radioBtnValue = MutableLiveData(false)

    var actions = Demo1Actions()

    /** 这个是 MVVM 中的  Model层，如没有网络或数据库需求，传NUll即可 */
    override fun getModel(): Class<BaseModel>? = null

    /** 创建自己的业务的Demo1Actions ，并转达给父类 ，若无需Actions，传NUll即可*/
    override fun onCreateActions(): ActivityActions = actions

    fun onClickBtn1(view: View) {
        finish()
    }

    fun onClickBtn2(view: View) {
        onBackPressedSupport()
    }

    fun onClickBtn3(view: View) {
        edtContent1.value = ""
    }

    fun onClickBtn4(view: View) {
        ToastUtils.showShort(edtContent1.value)
    }

    fun onClickBtn5(view: View) {
        actions.customAction.call(100)
    }

    fun onClickCheckBox(view: View) {
        if (checkBoxValue.value!!) {
            checkBoxText.value = "取消选择后隐藏下方按钮"
        } else checkBoxText.value = "选择后显示下方按钮"
    }

    val onEditTextChangeListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            println(s.toString())
        }
    }

}