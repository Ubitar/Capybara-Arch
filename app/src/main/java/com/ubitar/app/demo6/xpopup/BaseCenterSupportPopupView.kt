package com.ubitar.app.demo6.xpopup

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.*
import com.ubitar.app.demo6.vm.BasePopupViewModel
import com.ubitar.capybara.mvvm.control.IController
import com.lxj.xpopup.core.CenterPopupView
import java.lang.ref.WeakReference

abstract class BaseCenterSupportPopupView<V : ViewDataBinding, VM : BasePopupViewModel<*>>(iController: IController) :
    CenterPopupView(iController.getContext()!!), LifecycleObserver, ViewModelStoreOwner, LifecycleOwner {

    protected val parent = WeakReference(iController)

    private var viewModelStore: ViewModelStore? = null

    override fun onCreate() {
        super.onCreate()
        parent.get()?.lifecycle?.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        parent.get()?.lifecycle?.removeObserver(this)
    }

    override fun getLifecycle(): Lifecycle {
        return parent.get()?.lifecycle!!
    }

    override fun getViewModelStore(): ViewModelStore {
        if (viewModelStore == null) viewModelStore = ViewModelStore()
        return viewModelStore!!
    }


}