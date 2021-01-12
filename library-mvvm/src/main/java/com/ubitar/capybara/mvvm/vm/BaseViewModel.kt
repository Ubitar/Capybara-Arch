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

    /** 是否已初始化数据 */
    internal var isUnInitData = true

    init {
        createModel()
    }

    lateinit var lifecycle: WeakReference<LifecycleOwner>

    override fun injectLifecycleOwner(lifecycle: LifecycleOwner) {
        this.lifecycle = WeakReference(lifecycle)
    }

    /** 初始化事件 */
    override fun initEvent(lifecycle: LifecycleOwner) {

    }

    /** 初始化数据  */
    override fun initData() {
        isUnInitData = false
    }

    /** 创建数据层 Model */
    private fun createModel() {
        model = getModel()?.constructors?.get(0)?.newInstance(this) as M? ?: BaseModel(
            this
        ) as M
    }

    override fun onCleared() {
        lifecycle.clear()
        super.onCleared()
    }
}