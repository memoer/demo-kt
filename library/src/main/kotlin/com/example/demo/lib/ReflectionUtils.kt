package com.example.demo.lib

import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

class ReflectionUtils {
    companion object {
        fun <T : Any> setPropertyValue(instance: T, propertyName: String, value: Any) {
            val property = instance::class.memberProperties.find { it.name == propertyName }
                ?.apply { isAccessible = true } as? KMutableProperty1
                ?: throw IllegalArgumentException("Property $propertyName not found or not mutable")
            property.setter.call(instance, value)
        }
    }
}
