package com.ubitar.capybara.mvvm.control.impl

import com.ubitar.capybara.mvvm.CMVVM
import com.ubitar.capybara.mvvm.control.Controllable
import com.ubitar.capybara.mvvm.control.IControllable
import com.ubitar.capybara.mvvm.control.IController
import com.ubitar.capybara.mvvm.control.IProvider

class ControllableProvider private constructor(controller: IController) :
    IProvider {

    private lateinit var controllable: IControllable

    init {
        createControllable(controller)
    }

    private fun getControlType(controller: IController): Controllable? {
        return controller::class.java.getAnnotation(Controllable::class.java)
    }

    private fun createControllable(controller: IController) {
        val control=   getControlType(controller)

        if (control != null) {
            controllable = control.type.java.newInstance() as IControllable
        } else  {
            controllable = CMVVM.getInstance().controllable.newInstance() as IControllable
        }

        if(controllable is BaseControllable)
            (controllable as BaseControllable).injectController(controller)
    }


    override fun get(): IControllable {
        return controllable
    }

    companion object {

        fun with(controller: IController): ControllableProvider {
            return ControllableProvider(controller)
        }
    }
}