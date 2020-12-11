package com.ubitar.app.demo6.xpopup

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ubitar.app.demo6.vm.BasePopupViewModel
import com.ubitar.capybara.mvvm.IView
import com.ubitar.capybara.mvvm.control.IController
import java.lang.reflect.ParameterizedType

abstract class BaseCenterMvvMPopupView<V : ViewDataBinding, VM : BasePopupViewModel<*>>(iController: IController) :
    BaseCenterSupportPopupView<V, VM>(iController), IView, IController {

    protected lateinit var binding: V
    protected lateinit var viewModel: VM

    override fun getImplLayoutId(): Int {
        return getLayoutId(LayoutInflater.from(context), null)
    }

    override fun onCreate() {
        super.onCreate()
        binding = getDataBinding(LayoutInflater.from(context), centerPopupContainer, null)
        viewModel = getViewModel()
        //关联ViewModel
        binding.setVariable(getViewModelId(), viewModel)
        //支持LiveData绑定xml，数据改变，UI自动会更新
        binding.lifecycleOwner = this
        viewModel.injectLifecycleOwner(this)
        //创建ViewModel后
        onCreatedViewModel()
        viewModel.injectBaseActions(viewModel.onCreateActions())
        // 注册内容监听前 */
        onBeforeObservable()
        //私有的ViewModel与View的契约事件回调逻辑
        onBindObservable()
    }

    /**
     * 创建ViewModel后
     */
    override fun onCreatedViewModel() {
    }

    /**
     * 在注册ViewModel与View的回调事件前
     */
    override fun onBeforeObservable() {
    }

    override fun onBindObservable() {
        viewModel.getBaseActions().postAction.observe(this, Observer {
            post(it)
        })
        viewModel.getBaseActions().dismissAction.observe(this, Observer {
            dismiss()
        })
        viewModel.getBaseActions().showAction.observe(this, Observer {
            show()
        })
        viewModel.getBaseActions().showLoadingAction.observe(this, Observer {
            showLoading(it.isCancelEnable, it.isBackEnable, it.dismissListener, it.extra)
        })
        viewModel.getBaseActions().hideLoadingAction.observe(this, Observer {
            hideLoading()
        })
        viewModel.getBaseActions().showMessageAction.observe(this, Observer {
            showMessage(it.text, it.onDismissListener, it.extra)
        })
        viewModel.getBaseActions().showSuccessAction.observe(this, Observer {
            showSuccess(it.text, it.onDismissListener, it.extra)
        })
        viewModel.getBaseActions().showFailAction.observe(this, Observer {
            showFail(it.text, it.onDismissListener, it.extra)
        })
    }

    override fun onDestroy() {
        binding.unbind()
    }

    override fun <T : ViewDataBinding> getDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): T {
        return DataBindingUtil.bind(container!!.getChildAt(container.childCount - 1))!!
    }

    override fun <T : ViewModel> getViewModel(): T {
        val type = this.javaClass.genericSuperclass
        val modelClass = if (type is ParameterizedType) {
            type.actualTypeArguments[1] as Class<*>
        } else {
            //如果没有指定泛型参数，则默认使用BaseViewModel
            AndroidViewModel::class.java
        } as Class<T>
        return   ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(context!!.applicationContext as Application)).get(modelClass)
    }

}