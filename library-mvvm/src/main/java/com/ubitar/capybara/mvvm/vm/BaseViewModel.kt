package com.ubitar.capybara.mvvm.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import com.ubitar.capybara.mvvm.IViewModel
import com.ubitar.capybara.mvvm.model.BaseModel
import java.lang.ref.WeakReference

abstract class BaseViewModel<M : BaseModel>(application: Application) :
    AndroidViewModel(application),
    IViewModel<M> {

    protected lateinit var model: M

    private  lateinit var lifecycle: WeakReference<LifecycleOwner>

    init {
        createModel()
    }

    override fun injectLifecycleOwner(lifecycle: LifecycleOwner) {
        this.lifecycle = WeakReference(lifecycle)
    }

    override fun onCleared() {
        lifecycle.clear()
        super.onCleared()
    }

    /** 创建数据层 Model */
    private fun createModel() {
        model = getModel()?.constructors?.get(0)?.newInstance(this) as M? ?: BaseModel(
            this
        ) as M
    }

    fun requireLifecycle():LifecycleOwner{
        return lifecycle.get()!!
    }

    fun getLifecycle():LifecycleOwner?{
        return lifecycle.get()
    }

}