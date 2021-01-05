package com.ubitar.app.launcher

import android.app.ActivityOptions
import android.app.Application
import android.content.Intent
import android.view.View
import com.ubitar.app.R
import com.ubitar.app.demo1.Demo1Activity
import com.ubitar.app.demo2.Demo2Activity
import com.ubitar.app.demo3.Demo3Activity
import com.ubitar.app.demo4.Demo4Activity
import com.ubitar.app.demo5.Demo5Activity
import com.ubitar.app.demo6.Demo6Activity
import com.ubitar.capybara.mvvm.action.ActivityActions
import com.ubitar.capybara.mvvm.model.BaseModel
import com.ubitar.capybara.mvvm.vm.base.BaseActivityViewModel


class MainViewModel(application: Application) : BaseActivityViewModel<BaseModel>(application) {

    private val activityOptions=ActivityOptions.makeCustomAnimation(application, R.anim.h_fragment_enter,R.anim.h_fragment_exit)

    override fun getModel(): Class<BaseModel>? = null

    override fun onCreateActions(): ActivityActions? = null

    fun onClickBtn1(view: View) {
        val intent = Intent(getApplication<Application>(), Demo1Activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        getApplication<Application>().startActivity(intent,activityOptions.toBundle())
    }

    fun onClickBtn2(view: View) {
        val intent = Intent(getApplication<Application>(), Demo2Activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        getApplication<Application>().startActivity(intent,activityOptions.toBundle())
    }

    fun onClickBtn3(view: View) {
        val intent = Intent(getApplication<Application>(), Demo3Activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        getApplication<Application>().startActivity(intent,activityOptions.toBundle())
    }

    fun onClickBtn4(view: View) {
        val intent = Intent(getApplication<Application>(), Demo4Activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        getApplication<Application>().startActivity(intent,activityOptions.toBundle())
    }

    fun onClickBtn5(view: View) {
        val intent = Intent(getApplication<Application>(), Demo5Activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        getApplication<Application>().startActivity(intent,activityOptions.toBundle())
    }

    fun onClickBtn6(view: View) {
        val intent = Intent(getApplication<Application>(), Demo6Activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        getApplication<Application>().startActivity(intent,activityOptions.toBundle())
    }

}