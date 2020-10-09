package com.ubitar.app.demo4

import com.ubitar.app.demo4.network.Demo4Repository
import com.ubitar.capybara.mvvm.model.BaseModel
import com.ubitar.capybara.network.RetryWhenFunction
import com.ubitar.capybara.network.compose.ResponseCompose
import com.ubitar.capybara.network.compose.SchedulerCompose
import com.ubitar.capybara.network.utils.AutoDisposeUtil

class Demo4Model(
    private val viewModel: Demo4ViewModel
) : BaseModel(viewModel) {

    private val demo4Repository = Demo4Repository()

    fun toLogin(account: String, password: String) {
        demo4Repository.login(account, password)
            .compose(SchedulerCompose.io2main())
            .compose(ResponseCompose.parseResult())//过滤数据
            .retryWhen(RetryWhenFunction(3000, 3))//网络问题重试请求
            .doOnSubscribe { viewModel.showLoading() }
            .doFinally { viewModel.hideLoading() }
            .`as`(AutoDisposeUtil.fromOnDestroy(viewModel.lifecycle.get()!!))
            .subscribe({
                viewModel.afterLoginSuccess()
            }, {
                println(it)
            })
    }

    fun toLogout(account: String) {
        demo4Repository.logout(account)
            .compose(SchedulerCompose.io2main())
            .compose(ResponseCompose.parseResult())//过滤数据
            .retryWhen(RetryWhenFunction(3000, 3))//网络问题重试请求
            .doOnSubscribe { viewModel.showLoading() }
            .doFinally { viewModel.hideLoading() }
            .`as`(AutoDisposeUtil.fromOnDestroy(viewModel.lifecycle.get()!!))
            .subscribe({
                viewModel.afterLogoutSuccess()
            }, {
                println(it)
            })
    }

    override fun onCleared() {
        super.onCleared()

    }
}