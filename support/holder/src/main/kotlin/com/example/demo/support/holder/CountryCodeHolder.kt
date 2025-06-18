package com.example.demo.support.holder

object CountryCodeHolder {
    private val threadLocal: ThreadLocal<CountryCode> = ThreadLocal<CountryCode>()

    fun set(countryCode: CountryCode) {
        threadLocal.set(countryCode)
    }

    fun get(): CountryCode = threadLocal.get() ?: CountryCode.KR

    fun remove() {
        threadLocal.remove()
    }
}
