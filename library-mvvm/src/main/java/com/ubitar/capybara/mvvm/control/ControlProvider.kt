package com.ubitar.capybara.mvvm.control

import android.util.Log

class ControlProvider(controller: IController) : IProvider {

    private lateinit var controllable: IControllable

    init {
       val control= getCustomControl(controller)
        createControllable(control)
    }

    private fun getCustomControl(controller: IController): Control? {
        return controller::class.java.getAnnotation(Control::class.java)
    }

    private fun createControllable(control: Control?) {
        if (control != null) {
            controllable = control.type.java.newInstance() as IControllable
        } else if (config != null) {
            controllable = config!!.getGlobalControl()?.javaClass?.newInstance()!!
        } else {
            Log.e(javaClass.simpleName, "需要实现Controllable，才能使用IController接口中的方法")
        }
    }


    override fun get(): IControllable {
        return controllable
    }

    companion object {

        private var config: ControlConfig? = null

        fun setGlobalConfig(config: ControlConfig) {
            this.config = config
        }
    }
}