package com.ubitar.capybara.mvvm.control

class ControlConfig {
    private var globalControl: IControllable? = null

    fun setGlobalControl(globalControl: IControllable): ControlConfig {
        this.globalControl = globalControl
        return this
    }

    fun getGlobalControl(): IControllable? {
        return globalControl
    }
}