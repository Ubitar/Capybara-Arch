package com.ubitar.capybara.network.repository

import com.ubitar.capybara.network.NetworkManager

abstract class BaseRepository<T> {

    protected var repository: T

    init {
        repository = NetworkManager.getRequest(getApi())
    }

    protected abstract fun  getApi(): Class<T>

}