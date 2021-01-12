package com.ubitar.capybara.mvvm.control.impl

import com.ubitar.capybara.mvvm.control.IControllable
import com.ubitar.capybara.mvvm.control.IController
import java.lang.ref.WeakReference

abstract class BaseControllable : IControllable {

    lateinit var controller:IController

    fun injectController(controller: IController){
        this.controller= controller
    }

}