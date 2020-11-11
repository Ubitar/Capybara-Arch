package com.ubitar.capybara.mvvm.control

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class ControlProvider private constructor(controller: IController) : IProvider {

    private lateinit var controllable: IControllable

    init {
       val control= getControlType(controller)
        createControllable(control)

        bindLifecycle(controller)
    }

    private fun bindLifecycle(lifecycle:LifecycleOwner){
        lifecycle.lifecycle.addObserver(object :LifecycleEventObserver{
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                when(event){
                    Lifecycle.Event.ON_DESTROY->{
                        controllable.onDestroy()
                        source.lifecycle.removeObserver(this)
                    }
                }
            }

        })
    }

    private fun getControlType(controller: IController): Control? {
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

        fun with(controller: IController):ControlProvider{
            return ControlProvider(controller)
        }
    }
}