package com.ubitar.capybara.network.repository

import com.ubitar.capybara.network.NetworkManager

abstract class BaseRepository<T> {

    protected var repository: T

    init {
        repository = NetworkManager.getInstance().createService(getCreatorTag(), getApi())
    }

    protected abstract fun getApi(): Class<T>

    protected abstract fun getCreatorTag(): String

}