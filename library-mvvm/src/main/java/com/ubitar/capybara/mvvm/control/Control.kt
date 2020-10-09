package com.ubitar.capybara.mvvm.control

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Control(val type: KClass<*> = IControllable::class)