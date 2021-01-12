package com.ubitar.capybara.mvvm

import com.ubitar.capybara.mvvm.control.impl.EmptyControllable
import com.weikaiyun.fragmentation.Fragmentation

class CMVVM private constructor() {

 lateinit   var controllable: Class<*>

    private constructor(builder: CMVVMBuilder) : this() {
        controllable = builder.controllable?: EmptyControllable::class.java
    }

    class CMVVMBuilder internal constructor() {

       internal var controllable: Class<*>? = null

        fun setGlobalControllable(controllable: Class<*>): CMVVMBuilder {
            this.controllable = controllable
            return this
        }

        fun setFragmentation(fragmentation: Fragmentation):CMVVMBuilder{
            //NOTHING
            return this
        }

        fun install() {
            INSTANCE = CMVVM(this)
        }

    }

    companion object {
        @Volatile
        private var INSTANCE: CMVVM? = null

        fun getInstance() = INSTANCE ?: synchronized(this) {
            INSTANCE ?: CMVVM(CMVVMBuilder()).also { INSTANCE = it }
        }

        fun builder(): CMVVMBuilder {
            return CMVVMBuilder()
        }
    }
}