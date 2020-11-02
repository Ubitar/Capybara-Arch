package com.ubitar.app.demo4

import com.ubitar.app.common.AppResponseCompose
import com.ubitar.app.demo4.network.Demo4Repository
import com.ubitar.app.demo4.network.bean.UserBean
import com.ubitar.capybara.mvvm.model.BaseModel
import com.ubitar.capybara.network.function.RetryWhenFunction
import com.ubitar.capybara.network.bean.IBaseResponse
import com.ubitar.capybara.network.compose.SchedulerCompose

class Demo4Model(
    private val viewModel: Demo4ViewModel
) : BaseModel(viewModel) {

    private val demo4Repository = Demo4Repository()

    fun toLogin(account: String, password: String) {
        demo4Repository.login(account, password)
            .compose(SchedulerCompose.io2main())
            .retryWhen(RetryWhenFunction(3000, 3))//网络问题重试请求
            .compose(AppResponseCompose().parseResult<UserBean>())//过滤数据
            .doOnSubscribe { viewModel.showLoading() }
            .doFinally { viewModel.hideLoading() }
            .subscribe({
                viewModel.afterLoginSuccess()
            }, {
                println(it)
            }).isDisposed
    }

    fun toLogout(account: String) {
        demo4Repository.logout(account)
            .compose(SchedulerCompose.io2main())
            .retryWhen(RetryWhenFunction(3000, 3))//网络问题重试请求
            .compose(AppResponseCompose().parseResult<IBaseResponse>())//过滤数据
            .doOnSubscribe { viewModel.showLoading() }
            .doFinally { viewModel.hideLoading() }
            .subscribe({
                viewModel.afterLogoutSuccess()
            }, {
                println(it)
            }).isDisposed
    }

    override fun onCleared() {
        super.onCleared()

    }
}